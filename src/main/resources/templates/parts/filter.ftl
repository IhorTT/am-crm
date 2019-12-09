<#macro showFilter map>

<div class="my-2">
    <form method="get" class="form-inline ml-2" action=${map}>
        <input type="text" name="filter" class="form-control" value="${filter}"
               placeholder="Поиск">
        <button type="submit" class="btn btn-primary btn-sm ml-1" name="btn" value="btnFind">Нати</button>
        <button type="submit" class="btn btn-primary btn-sm ml-1" name="btn" value="btnCancel">X</button>
    </form>
</div>

</#macro>