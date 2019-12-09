<#macro login>
    <a class="btn btn-primary btn-sm" href="/login">Войти</a>
</#macro>

<#macro logout>
 <form action="/logout" method="post">
     <input type="hidden" name="_csrf" value="${_csrf.token}">
     <button class="btn btn-primary btn-sm" type="submit">Выйти</button>
 </form>
</#macro>

