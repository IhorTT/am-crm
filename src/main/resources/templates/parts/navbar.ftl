<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">AM-CRM
    <#--<img src="/docs/4.3/assets/brand/bootstrap-solid.svg" width="30" height="30" alt="">-->
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/events">События</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/client">Клиенты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/persons">Контакты</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/settings">Настройки</a>
            </li>

        </ul>
        <div class="navbar-text mr-2">${userName}</div>
        <#if userName=="">
            <@l.login />
        <#else >
            <@l.logout />
        </#if>

    </div>
</nav>