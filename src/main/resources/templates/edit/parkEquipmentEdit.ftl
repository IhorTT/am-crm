<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>

<h6> Редактирование парка техники</h6>
<h6> Клиент: ${client.name} </h6>
<form class="ml-2" action="/client/editEP" method="post">

    <div class="row form-inline  pt-2">
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
        <select class="custom-select col-lg-1 ml-3" id="typeOfAgrMach" name="typeOfAgrMachId">
            <option value=""></option>
                <#list typesOfAgrMach as typeOfAgrMach>
                    <#if typeOfAgrMach.id == equipment.typeOfAgrMach.id>
                        <option selected value=${typeOfAgrMach.id}>${typeOfAgrMach.name}</option>
                    <#else>
                        <option value=${typeOfAgrMach.id}>${typeOfAgrMach.name}</option>
                    </#if>
                </#list>
        </select>

        <label class="ml-2 mr-3" for="model">Модель:</label>
        <input type="text" class="form-control col-lg-1 ml-4" id="model"
               name="model" value="${equipment.model}" placeholder="Модель">
    </div>

    <div class="row form-inline pt-2">
        <label class="ml-2 mr-3" for="serialNumber">Серийный №:</label>
        <input type="text" class="form-control col-lg-1 ml-2" id="serialNumber"
               name="serialNumber" value="${equipment.serialNumber}" placeholder="Серийный номер">

        <label class="ml-2" for="count">К-во:</label>
        <input type="text" class="form-control col-lg-1 ml-2" id="count"
               name="count" value="${equipment.count}" placeholder="Количество">

        <label class="ml-2" for="yearOfManufacture">Год выпуска:</label>
        <input type="text" class="form-control col-lg-1 ml-2" id="yearOfManufacture"
               name="yearOfManufacture" value="${equipment.yearOfManufacture}" placeholder="Год выпуска">
    </div>

    <div class="row form-inline  pt-2">

        <label class="ml-2 mr-1" for="description">Комментарий:</label>
        <input type="text" class="form-control col-lg-2 ml-3" id="description"
               name="description" value="${equipment.description}" placeholder="Комментарий">

        <label class="" for="dateCreation">Дата записи:</label>
        <input type="datetime-local" class="form-control " id="dateCreation" name="dateCreation"
               value="${equipment.dateCreation.editForm}" <#if isAdmin==false>readonly</#if>>

    </div>

    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <input type="hidden" name="id" value="${equipment.id}">
    <input type="hidden" name="client_id" value="${client.id}">

    <div class="mt-2">
        <button type="submit" class="btn btn-primary btn-sm" id="btnOk" name="btn" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>

</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("manufacturer").setAttribute('disabled', 'disabled')
        document.getElementById("typeOfAgrMach").setAttribute('disabled', 'disabled')
        document.getElementById("model").setAttribute('disabled', 'disabled')
        document.getElementById("serialNumber").setAttribute('disabled', 'disabled')
        document.getElementById("count").setAttribute('disabled', 'disabled')
        document.getElementById("yearOfManufacture").setAttribute('disabled', 'disabled')
        document.getElementById("description").setAttribute('disabled', 'disabled')
        document.getElementById("dateCreation").setAttribute('readonly', 'readonly')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>

<#if isViewOnly==false> </#if>