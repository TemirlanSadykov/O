<#import "partials/main.ftlh" as main>
<@main.renderWith>
<#--<@main.renderWith scripts=["scripts/.js"]>-->
    <div class="mx-auto mt-3 w-50">
        <form id="login-form" action="/" method="post">

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3 class="text-center">Войти</h3>
            <div class="form-group">
                <label>Введите Логин:</label>
                <input type="text" name="username" class="form-control" placeholder="Логин" autofocus>
            </div>
            <div class="form-group">
                <label>Введите пароль:</label>
                <input type="password" name="password" class="form-control" placeholder="Пароль">
            </div>
            <button name="login" class="btn btn-primary btn-block">Войти</button>

            <div>
                <#if error!false >
                <div class="alert alert-warning mt-1" style="color: red" role="alert">Вы ввели неверный логин или пароль</div>
                </#if>
            </div>
        </form>
    </div>
</@main.renderWith>