<#import "../parts/common.ftl" as c>
<#import "../parts/filter.ftl" as f>
<#include "../parts/security.ftl">
<@c.page>

    <@f.showFilter "/persons"/>

<div class="form-row col-md-6">
    <div class="tablescrollable">
        <table class="table table-hover table-condensed">
            <thead class="thead-light">
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Должность</th>
                <th scope="col">Телефон</th>
                <th scope="col">e-mail</th>
                <th scope="col">Управление</th>
            </tr>
            </thead>

            <#list persons as person>
                <tr>
                    <td>${person.name}</td>
                    <td>${person.position.name}</td>
                    <td>${person.phone}</td>
                    <td>${person.email}</td>
                    <td>
                        <a href="/persons/${person.client.id}/editCP/${person.id}?page=persons"> Edit </a>
                        <#if isViewOnly==false>
                            <a href="/persons/${person.client.id}/delCP/${person.id}?page=persons"> Delete </a>
                        </#if>
                    </td>
                </tr>
            </#list>

        </table>
    </div>
</div>

    <#if isViewOnly==false>
    <div>
        <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapse" role="button" aria-expanded="false"
           aria-controls="collapse">
            Добавить
        </a>
        <div class="collapse  ml-1 mt-3" id="collapse">

            <form method="post" class="form-inline ml-3" action="/persons/new">
                <div> Клиент:</div>

                <select class="custom-select ml-2" id="client" name="client_id">
                    <option value=""></option>
                <#list clients as client>
                    <option value=${client.id}>${client.name}</option>
                </#list>
                </select>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-sm ml-1">Ок</button>
            </form>

        </div>
    </div>
    </#if>

</@c.page>