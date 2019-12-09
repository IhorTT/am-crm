<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">

<@c.page>

<h6> Редактирование контактного лица</h6>
<h6> Клиент: ${person.client.name} </h6>
<form class="" action="/persons/editCP" method="post">

    <div class="form-inline  pt-2">
        <label class="mr-4" for="inputLastName">Фамилия:</label>
        <input type="text" class="form-control " id="inputLastName"
               name="lastName" value="${person.lastName}" placeholder="Фамилия">
        <label class="pl-4 pr-2" for="inputFirstName">Имя:</label>
        <input type="text" class="form-control" id="inputFirstName"
               name="firstName" value="${person.firstName}" placeholder="Имя">
        <label class="pl-1 pr-2" for="inpuPatronymic">Отчество:</label>
        <input type="text" class="form-control " id="inpuPatronymic"
               name="patronymic" value="${person.patronymic}" placeholder="Отчество">
    </div>

    <div class="form-inline pt-2">
        <label class="pr-4" for="inputPhone">Телефон:</label>
        <input type="text" class="form-control ml-1" id="inputPhone"
               name="phone" value="${person.phone}" placeholder="Телефон">
        <label class="ml-1" for="inputEmail">@-mail:</label>
        <input type="text" class="form-control ml-2 " id="inputEmail" name="email" value="${person.email}"
               placeholder="@-mail">
        <label class="ml-1 mr-2" for="inputDate">Дт. рожд.:</label>
        <input type="date" class="form-control pr-4" id="inputDate" name="birthDate" value="${person.birthDate}">
    </div>

    <div class="form-inline pt-2">
        <label class="pr-2" for="position">Должность:</label>
        <select class="custom-select col-lg-1" id="position" name="positionId">
            <option value=""></option>
                <#list positions as position>
                    <#if position.id == person.position.id>
                        <option selected value=${position.id}>${position.name}</option>
                    <#else>
                        <option value=${position.id}>${position.name}</option>
                    </#if>
                </#list>
        </select>

    </div>

    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <input type="hidden" name="id" value="${person.id}">
    <input type="hidden" name="client_id" value="${person.client.id}">
    <input type="hidden" name="page" value="${page}">

    <div class="pt-2">
        <button type="submit" class="btn btn-primary btn-sm" name="btn" id="btnOk" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>

</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("inputLastName").setAttribute('readonly', 'readonly')
        document.getElementById("inputFirstName").setAttribute('readonly', 'readonly')
        document.getElementById("inpuPatronymic").setAttribute('readonly', 'readonly')
        document.getElementById("inputPhone").setAttribute('readonly', 'readonly')
        document.getElementById("inputEmail").setAttribute('readonly', 'readonly')
        document.getElementById("inputDate").setAttribute('readonly', 'readonly')
        document.getElementById("position").setAttribute('disabled', 'disabled')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>
