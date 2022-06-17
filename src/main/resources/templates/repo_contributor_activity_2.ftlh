<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include 'common/common.ftlh'>
</head>
<body onload="load()">
<#include 'common/menu.ftlh'>
<div>
    <canvas id="bar-chart"></canvas>
</div>
</body>
<script>
    function load() {
        const params = new URLSearchParams(window.location.search)
        let repo = params.get('repo')
        let owner = params.get('owner')

        $.ajax({
            url: '/repos/' + owner + '/' + repo + '/stats/contributors/activity',
            method: 'GET',
            success: (data) => {
                let lastNonZero = 0;
                data.weeks.forEach((w, i) => {
                    if (w.c !== 0) lastNonZero = i;
                })

                let weeks = data.weeks.slice(0, lastNonZero + 1);
                let labels = weeks.map(w => {
                    let timestamp = w.w
                    return timeConverter(timestamp)
                });
                let datasets = [
                    {
                        label: 'Additions',
                        data: weeks.map(w => w.a),
                        backgroundColor: 'rgb(0, 255, 0)',
                    },
                    {
                        label: 'Deletions',
                        data: weeks.map(w => w.d),
                        backgroundColor: 'rgb(255, 0, 0)',
                    },
                ]
                let chart_data = {
                    labels: labels,
                    datasets: datasets
                };
                loadBar(chart_data);
            },
            error: function () {
                alert("Ошибка");
            },
        })

        function loadBar(data) {
            const config = {
                type: 'bar',
                data: data,
                options: {
                    plugins: {
                        title: {
                            display: true,
                            text: 'Chart.js Bar Chart - Stacked'
                        },
                    },
                    responsive: true,
                    scales: {
                        x: {
                            stacked: true,
                        },
                        y: {
                            stacked: true
                        }
                    }
                }
            };

            const myChart = new Chart(
                document.getElementById('bar-chart'),
                config
            );
        }

    }

    function timeConverter(UNIX_timestamp) {
        var a = new Date(UNIX_timestamp * 1000);
        var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        var year = a.getFullYear();
        var month = months[a.getMonth()];
        var date = a.getDate();
        return date + ' ' + month + ' ' + year;
    }
</script>
</html>