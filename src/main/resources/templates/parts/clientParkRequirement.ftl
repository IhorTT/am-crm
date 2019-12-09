<div class="form-row col-md-8 tablescrollable">
    <table class="table table-hover table-condensed">
        <thead class="thead-light">
        <tr>
            <th scope="col">Производитель</th>
            <th scope="col">Вид техники</th>
            <th scope="col">Дата покупки</th>
            <th scope="col">Количество</th>
            <th scope="col">Комментарий</th>
            <th scope="col">Дата записи</th>
            <th scope="col">Управление</th>
        </tr>
        </thead>
            <#list client.requirementPark as ep>
               <tr>
                   <td>${ep.manufacturer.name}</td>
                   <td>${ep.typeOfAgrMach.name}</td>
                   <td>${ep.dateBuying}</td>
                   <td>${ep.count}</td>
                   <td>${ep.description}</td>
                   <td>${ep.dateCreation.listForm}</td>
                   <td>
                       <a href="/client/${client.id}/editRP/${ep.id}"> Edit </a>
                        <#if isViewOnly==false>
                            <a href="/client/${client.id}/delRP/${ep.id}"> Delete </a>
                        </#if>
                   </td>
               </tr>
            </#list>
    </table>
    <#if isViewOnly==false>
        <a class="btn btn-primary btn-sm" href="/client/${client.id}/editRP/0">Добавить</a>
    </#if>
</div>