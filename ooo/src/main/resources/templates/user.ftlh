<#import "partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <h3 class="text-center">Добро пожаловать в систему <b>${userName}</b></h3>
        <a href="/user/records" class="btn btn-primary btn-block">Просмотр всех заметок</a>
        <a href="/user/record" class="btn btn-primary btn-block doctors">Создать новую заметку</a>
    </div>
</@main.renderWith>