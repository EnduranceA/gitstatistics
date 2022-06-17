<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include 'common/common.ftlh'>
</head>
<body onload="load()">
<#include 'common/menu.ftlh'>
<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px"><h4>Рейтинг активности разработчиков</h4></div>
    <div class="row justify-content-md-center">
        <div class="col col-lg-12">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Имя участника</th>
                    <th scope="col">Кол-во коммитов</th>
                    <th scope="col">Кол-во добавлений</th>
                    <th scope="col">Кол-во удалений</th>
                </tr>
                </thead>
                <tbody>
                <#if characteristics.contributorsActivity??>
                    <#list characteristics.contributorsActivity as item>
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.commit}</td>
                            <td>${item.add}</td>
                            <td>${item.delete}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div class="row justify-content-md-center">
        <h4>Среднее время оценки pull-request: <span>${characteristics.averageTimeEstimationPR}</span> дней</h4>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Важные пакеты в кодовой базе</h4>
        <div class="col col-lg-12">
            <ul class="list-group">
                <#if characteristics.importantPackages??>
                    <#list characteristics.importantPackages as item>
                        <li class="list-group-item">${item}</li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Пакеты, склонные к ошибкам</h4>
        <div class="col col-lg-12">
            <ul class="list-group">
                <#if characteristics.packagesProneToBreakage??>
                    <#list characteristics.packagesProneToBreakage as item>
                        <li class="list-group-item">${item}</li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Стабильные пакеты</h4>
        <div class="col col-lg-12">
            <ul class="list-group">
                <#if characteristics.stablePackages??>
                    <#list characteristics.stablePackages as item>
                        <li class="list-group-item">${item}</li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Нестабильные пакеты</h4>
        <div class="col col-lg-12">
            <ul class="list-group">
                <#if characteristics.unstablePackages??>
                    <#list characteristics.unstablePackages as item>
                        <li class="list-group-item">${item}</li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Сбалансированные пакеты</h4>
        <div class="col col-lg-12">
            <ul class="list-group">
                <#if characteristics.balancedPackages??>
                    <#list characteristics.balancedPackages as item>
                        <li class="list-group-item">${item}</li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Атмосфера в команде</h4>
        <div class="col col-lg-12">
            <span>${characteristics.teamAtmosphere}</span>
        </div>
    </div>
</div>

<div class="container" style="padding-top: 20px">
    <div style="padding-bottom: 10px">
        <h4>Текучесть разработчиков</h4>
        <div class="col col-lg-12">
            <span>${characteristics.developerTurnoverPerYear}</span>
        </div>
    </div>
</div>
<div class="container" style="padding-top: 20px; padding-bottom: 20px">
    <div style="padding-bottom: 10px"><h4>Разработчики, которые не показывали результат</h4></div>
    <div class="row justify-content-md-center">
        <div class="col col-lg-12">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Имя участника</th>
                    <th scope="col">Дата</th>
                </tr>
                </thead>
                <tbody>
                <#if characteristics.developersPerformPoorly??>
                    <#list characteristics.developersPerformPoorly as item>
                        <tr>
                            <td>${item.author}</td>
                            <td>${item.date}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#--<div class="container" style="padding-top: 20px; padding-bottom: 20px">-->
<#--    <div style="padding-bottom: 10px"><h4>Переработки разработчиков</h4></div>-->
<#--    <div class="row justify-content-md-center">-->
<#--        <div class="col col-lg-12">-->
<#--            <table class="table">-->
<#--                <thead class="thead-dark">-->
<#--                <tr>-->
<#--                    <th scope="col">Имя участника</th>-->
<#--                    <th scope="col">Кол-во коммитов</th>-->
<#--                    <th scope="col">Кол-во добавлений</th>-->
<#--                    <th scope="col">Кол-во удалений</th>-->
<#--                </tr>-->
<#--                </thead>-->
<#--                <tbody>-->
<#--                <#if characteristics.developersRecycles??>-->
<#--                    <#list characteristics.developersRecycles as item>-->
<#--                        <tr>-->
<#--                            <td>${item.name}</td>-->
<#--                            <td>${item.commit}</td>-->
<#--                            <td>${item.add}</td>-->
<#--                            <td>${item.delete}</td>-->
<#--                        </tr>-->
<#--                    </#list>-->
<#--                </#if>-->
<#--                </tbody>-->
<#--                </tbody>-->
<#--            </table>-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

</body>
</html>