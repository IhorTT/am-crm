<#include "../parts/security.ftl">
<#macro showThesarus caption list nameThes tab>
     <div class="form-row col-md-6">
         <div class="tablescrollable">
             <table class="table table-hover table-condensed">
                 <thead class="thead-light">
                 <tr>
                     <th scope="col">${caption}</th>
                     <th scope="col">Управление</th>
                 </tr>
                 </thead>

            <#list list as el>
                <tr>
                    <td>${el.name}</td>
                    <td>
                        <a href="/settings/${nameThes}/edit/?id=${el.id}&tab=${tab}"> Edit </a>
                        <#if isViewOnly==false>
                        <a href="/settings/${nameThes}/delete/?id=${el.id}&tab=${tab}"> Delete </a>
                        </#if>
                    </td>
                </tr>
            </#list>

             </table>
             <#if isViewOnly==false>
                <a class="btn btn-primary btn-sm" href="/settings/${nameThes}/edit/?id=0&tab=${tab}">Добавить</a>
             </#if>
         </div>
     </div>
</#macro>
