<#import "../parts/common.ftl" as c>
<#import "../parts/filter.ftl" as f>
<#include "../parts/security.ftl">

<@c.page>
    <@f.showFilter "/client"/>

<div class="form-row col-md-6">
    <div class="tablescrollable">
        <table class="table table-hover table-condensed">
            <thead class="thead-light">
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Код по ЕГРПОУ</th>
                <th scope="col">ИНН</th>
                <th scope="col">Ответственный</th>
                <th scope="col">Управление</th>
            </tr>
            </thead>
        <#list clients as client>
            <tr>
                <td>${client.name}</td>
                <td>${client.edpnou}</td>
                <td>${client.itn}</td>
                <td>${client.manager.name}</td>
                <td>
                    <a href="/client/${client.id}?tabCl=main"> Edit </a>
                <#if isViewOnly==false> <a href="/client/del/${client.id}"> Delete </a> </#if>
                </td>

            </tr>
        </#list>
        </table>
    </div>
</div>
    <#if isViewOnly==false> <a class="btn btn-primary btn-sm ml-3" href="/client/0">Добавить</a> </#if>
</@c.page>