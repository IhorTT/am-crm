<#import "../parts/common.ftl" as c>
<@c.page>
<h6 class="mr-2"> Пользователь: ${user.username} </h6>
<form class="ml-2" action="/user/edit" method="post">
    <div class="form-inline  pt-2">
        <label class="">Логин:</label>
        <input type="text" class="form-control form-row col-md-1 ml-3" name="userName" value="${user.username}">
    </div>
    <div class="form-inline  pt-2">
        <label class="">Пароль:</label>
        <input type="password" class="form-control form-row col-md-1 ml-2" name="password" value="${user.password}">

    </div>


    <label class="pt-2">Роли:</label>
    <div class="ml-5">
        <#list roles as role>
            <div>
                <input type="checkbox" class="ml-2"
                       name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role.label}
            </div>
        </#list>
    </div>

    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" value="${_csrf.token}" name="_csrf">

    <div class="pt-2">
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>
</form>
</@c.page>