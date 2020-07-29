<#import "partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">

                    <form id="login-form" action="/user/record" method="post">

                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <input type="hidden" name="user" value="${userName}">
                    <h3 class="text-center">Создать новую запись</h3>
                    <div class="form-group">
                        <label>Введите название:</label>
                        <input type="text" name="name" class="form-control" placeholder="Название" autofocus>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'name'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Введите описание:</label>
                        <textarea type="text" name="description" class="form-control" placeholder="Описание" autofocus></textarea>
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'description'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label>Срок до:</label>
                        <input type="date" name="time" class="form-control">
                        <#if errors??>
                            <#list errors as fieldError>
                                <#if fieldError.field == 'time'>
                                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                    <button name="register" class="btn btn-primary btn-block">Создать</button>
        </form>
    </div>
</@main.renderWith>