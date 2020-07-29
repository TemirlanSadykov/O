<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h3>Список невыполненых записей</h3>
    <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Дата создания</th>
            <th scope="col">Дата планирования выполнения</th>
            <th scope="col">Статус</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as records>
            <#if records.status == false>
                <tr>
                    <th scope="row">${records?counter}</th>
                    <td><a href="/user/records/record/${records.id}">${records.name}</a></td>
                    <td>${records.timeNow?datetime}</td>
                    <td>${records.time?datetime}</td>
                    <td><a style="margin-top: 5px; color: white" class="btn btn-danger btn-block">Невыполнен</a></td>
                </tr>
            <#else>
                <tr>
                    <th scope="row">${records?counter}</th>
                    <td><a href="/user/records/record/${records.id}">${records.name}</a></td>
                    <td>${records.timeNow?datetime}</td>
                    <td>${records.time?datetime}</td>
                    <td><a style="margin-top: 5px; color: white" class="btn btn-success btn-block">Выполнен</a></td>
                </tr>
            </#if>

        </#list>
        </tbody>
    </table>
    <a href="/user/record" class="btn btn-primary btn-block doctors">Создать новую заметку</a>
    <#include "partials/pagination.ftlh">
</@main.renderWith>
<script>
    $(document).ready(function(){
        $("#Input").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Table tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>