<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>

<form class="ml-2" action="/settings/save" method="post">
    <h6> Справочник: ${name}->${object.name}</h6>

    <div class="row form-inline pt-2">
        <label class="ml-3" for="name">Наименование: </label>
        <input type="text" class="form-control col-lg-2 ml-2" id="name" name="name" value="${object.name}"
               placeholder="Наименование">
    </div>

    <input type="hidden" name="id" value="${object.id}">
    <input type="hidden" name="thesaurus" value="${thesaurus}">
    <input type="hidden" name="tab" value="${tab}">
    <input type="hidden" value="${_csrf.token}" name="_csrf">

    <div class="pt-2">
        <button type="submit" class="btn btn-primary btn-sm" id="btnOk" name="btn" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>

</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("name").setAttribute('disabled', 'disabled')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>
