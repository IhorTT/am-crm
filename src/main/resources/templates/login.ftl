<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    ${message?if_exists}
<form action="/login" method="post">
    <div class="container">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя пользователя: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Имя пользователя"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>
        <button class="btn btn-primary btn-sm" type="submit">Вход</button>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

</@c.page>
