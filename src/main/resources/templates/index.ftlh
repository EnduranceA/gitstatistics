<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <#include 'common/common.ftlh'>
</head>
<body>
<#include 'common/menu.ftlh'>
<div class="container mt-4">
    <div class="row">
        <h3>Введите username на GitHub:</h3>
        <div class="col-4">
            <input type="text" class="form-control" name="username-input" id="username-input">
        </div>
        <div class="col-1">
            <button class="btn btn-primary" onclick="loadUserRepos()">Загрузить</button>
        </div>
    </div>
    <div id="repo-list" class="mt-4 col-6">

    </div>
</div>
</body>
<script>
    let owner = '';

    function loadUserRepos() {
        let username_input = $('#username-input').val();
        $.ajax({
            url: '/users/' + username_input + '/repos',
            method: 'GET',
            success: (data) => {
                if (data.length === 0) {
                    alert("No repos found");
                    return
                }
                owner = username_input;
                let listContent = '';
                listContent += '<ul class="list-group">';
                data.forEach(r => {
                    listContent += '<li class="list-group-item">' +
                        '<a href="{link}">'.replace('{link}', getRepoPageLink(owner, r.name)) +
                        r.name +
                        '</a>' +
                        '</li>';
                })
                listContent += "<ul>"
                $('#repo-list').html(listContent);
            },
            statusCode: {
                404: function () {
                    alert("Репозитории не найдены");
                }
            },
            error: function () {
                alert("Ошибка");
            },
        })
    }

    function getRepoPageLink(owner, repoName) {
        return "/repo?owner={owner}&repo={repo}"
            .replace('{owner}', owner)
            .replace('{repo}', repoName);
    }

</script>
</html>