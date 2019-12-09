<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>
<h6> Редактирование контактной информации </h6>
<h6> Клиент: ${client.name} </h6>
<form class="" action="/client/editCI" method="post">

    <div class="form-inline">
        <div class="input-group ">
            <label class="mr-2" for="type">Вид:</label>
            <select class="custom-select" id="type" name="type">
                <#list ci_types as type_ci>
                    <#if type_ci.label == continfo.typeCI>
                        <option selected value=${type_ci}>${type_ci.label}</option>
                    <#else>
                        <option value=${type_ci}>${type_ci.label}</option>
                    </#if>
                </#list>
            </select>
        </div>
        <div class="form-group ml-2">
            <label for="inputDetails" class="sr-only">Представление</label>
            <input type="text" class="form-control" id="inputDetails"
                   name="details" value="${continfo.details}" placeholder="Представление">
        </div>
    </div>

    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <input type="hidden" name="id" value="${continfo.id}">
    <input type="hidden" name="client_id" value="${client.getId()}">

    <div class="pt-2">
        <button type="submit" class="btn btn-primary btn-sm" name="btn" id="btnOk" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>
</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        // document.getElementById("type").setAttribute('disabled', 'disabled')
        document.getElementById("inputDetails").setAttribute('readonly', 'readonly')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>