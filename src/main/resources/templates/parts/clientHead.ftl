<form class="" action="/client/edit" method="post">

    <div class="row form-inline pt-2">
        <label class="ml-4 mr-2" for="name">Наименование:</label>
        <input type="text" class="form-control col-lg-3 " id="name" name="name" value="${client.name}"
               placeholder="Наименование">
    </div>

    <div class="row form-inline pt-2">
        <label class="ml-4" for="edpnou">ЕГРПОУ:</label>
        <div class="ml-3">

        </div>
        <input type="text" class="form-control ml-5 col-lg-1" name="edpnou" id="edpnou"
               value="${client.edpnou}" placeholder="Код по ЕГРПОУ">

        <label class="ml-2" for="itn">ИНН:</label>
        <input type="text" class="form-control col-lg-1 ml-2" name="itn" id="itn" value="${client.itn}"
               placeholder="ИНН">
    </div>
    <div class="row form-inline pt-2">
        <label class="ml-4" for="manager">Отвественный:</label>
        <select class="custom-select col-lg-3 ml-3" id="manager" name="managerId" >
            <option value=""></option>
                <#list managers as manager>
                    <#if manager == client.manager>
                        <option selected value=${manager.id}>${manager.name}</option>
                    <#else>
                        <option value=${manager.id}>${manager.name}</option>
                    </#if>
                </#list>
        </select>
    </div>

    <input type="hidden" name="id" value="${client.id}">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <div class="pl-2 pt-2">
        <button type="submit" class="btn btn-primary btn-sm" name="btn" id="btnOk" value="btnOk">Ок</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" id="btnSave" value="btnSave">Сохранить</button>
        <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>
</form>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("name").setAttribute('disabled', 'disabled')
        document.getElementById("edpnou").setAttribute('disabled', 'disabled')
        document.getElementById("itn").setAttribute('disabled', 'disabled')
        document.getElementById("manager").setAttribute('disabled', 'disabled')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
        document.getElementById("btnSave").setAttribute('disabled', 'disabled')
    </script>
</#if>