<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Repository</title>
    <#include 'common/common.ftlh'>
</head>
<body>
<#include 'common/menu.ftlh'>
<div class="container mt-4">
    <p class="display-5">${repo.name}</p>
    <ul class="list-group col-8">
        <li class="list-group-item">
            <strong>Полное имя репозитория: </strong>${repo.fullName}
        </li>
        <li class="list-group-item">
            <strong>Описание: </strong>${repo.description!""}
        </li>
        <li class="list-group-item">
            <strong>Дата создания: </strong>${repo.createdAt}
        </li>
        <li class="list-group-item">
            <strong>Дата последнего изменения: </strong>${repo.pushedAt}
        </li>
        <li class="list-group-item">
            <strong>Видимость: </strong>${repo.visibility}
        </li>
        <li class="list-group-item">
            <strong>URL:</strong> <a href="${repo.htmlUrl}">${repo.htmlUrl}</a>
        </li>
        <li class="list-group-item">
            <strong>Характеристики командной разработки:</strong> <a href="/repo/activity?owner=${owner}&repo=${repo_name}">
                Посмотреть</a>
        </li>
    </ul>
</div>
</body>
</html>