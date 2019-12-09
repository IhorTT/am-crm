<div class="form-row col-md-6 tablescrollable">
    <table class="table table-hover table-condensed">
        <thead class="thead-light">
        <tr>
            <th scope="col">Вид</th>
            <th scope="col">Представление</th>
            <th scope="col">Управление</th>
        </tr>
        </thead>
            <#list client.contactInformation as ci>
               <tr>
                   <td>${ci.typeCI}</td>
                   <td>${ci.details}</td>
                   <td>
                       <a href="/client/${client.id}/editCI/${ci.id}"> Edit </a>
                        <#if isViewOnly==false>
                        <a href="/client/${client.id}/delCI/${ci.id}"> Delete </a>
                        </#if>
                   </td>
               </tr>
            </#list>
    </table>
    <#if isViewOnly==false>
        <a class="btn btn-primary btn-sm" href="/client/${client.id}/editCI/0">Добавить</a>
    </#if>
</div>