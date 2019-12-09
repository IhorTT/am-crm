<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>

<h6> Редактирование потребности в технике</h6>
<h6> Клиент: ${client.name} </h6>
<form class="ml-2" action="/client/editRP" method="post">

    <div class="row form-inline pt-2">
        <label class="ml-2" for="manufacturer">Производитель:</label>
        <select class="custom-select col-lg-1 ml-2" id="manufacturer" name="manufacturerId">
            <option value=""></option>
                <#list manufacturers as manufacturer>
                    <#if manufacturer.id == equipment.manufacturer.id>
                        <option selected value=${manufacturer.id}>${manufacturer.name}</option>
                    <#else>
                        <option value=${manufacturer.id}>${manufacturer.name}</option>
                    </#if>
                </#list>
        </select>

        <label class="ml-2" for="typeOfAgrMach">Вид:</label>
        <select class="custom-select col-lg-1 ml-2" id="typeOfAgrMach" name="typeOfAgrMachId">
            <option value=""></option>
                <#list typesOfAgrMach as typeOfAgrMach>
                    <#if typeOfAgrMach.id == equipment.typeOfAgrMach.id>
                        <option selected value=${typeOfAgrMach.id}>${typeOfAgrMach.name}</option>
                    <#else>
                        <option value=${typeOfAgrMach.id}>${typeOfAgrMach.name}</option>
                    </#if>
                </#list>
        </select>

    </div>

    <div class="row form-inline pt-2">
        <label class="ml-2 mr-1" for="dateBuying">Дата покупки:</label>
        <input type="date" class="form-control col-lg-1 ml-3" id="dateBuying" name="dateBuying"
               value=${equipment.dateBuying}>

        <label class="ml-2" for="count">К-во:</label>
        <input type="text" class="form-control col-lg-1 ml-1" id="count"
               name="count" value="${equipment.count}" placeholder="Количество">
    </div>

    <div class="row form-inline pt-2">

        <label class="ml-2" for="description">Комментарий:</label>
        <input type="text" class="form-control col-lg-2 ml-3" id="description"
               name="description" value="${equipment.description}" placeholder="Комментарий">
    </div>

    <div class="row form-inline pt-2">
        <label class="ml-2 mr-1" for="dateCreation">Дата записи:</label>
        <input type="datetime-local" class="form-control  ml-4" id="dateCreation" name="dateCreation"
               value="${equipment.dateCreation.editForm}" <#if isAdmin==false>readonly</#if>>

    </div>

    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <input type="hidden" name="id" value="${equipment.id}">
    <input type="hidden" name="client_id" value="${client.id}">
    <div class="pt-2">
        <button type="submit" class="btn btn-primary btn-sm" id="btnOk" name="btn" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>

</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("manufacturer").setAttribute('disabled', 'disabled')
        document.getElementById("typeOfAgrMach").setAttribute('disabled', 'disabled')
        document.getElementById("dateBuying").setAttribute('readonly', 'readonly')
        document.getElementById("count").setAttribute('disabled', 'disabled')
        document.getElementById("description").setAttribute('disabled', 'disabled')
        document.getElementById("dateCreation").setAttribute('readonly', 'readonly')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>