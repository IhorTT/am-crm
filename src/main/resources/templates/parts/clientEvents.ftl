<div class="form-row col-md-8 tablescrollable">
    <table class="table table-hover table-condensed">
        <thead class="thead-light">
        <tr>
            <th scope="col">Номер</th>
            <th scope="col">Дата</th>
            <th scope="col">Начало события</th>
            <th scope="col">Окончание события</th>
            <th scope="col">Вид события</th>
            <th scope="col">Контактное лицо</th>
            <th scope="col">Тема</th>
            <th scope="col">Управление</th>
        </tr>
        </thead>
            <#list client.events as event>
               <tr>
                   <td>${event.number}</td>
                   <td>${event.date.listForm}</td>
                   <td>${event.dateStart.listForm}</td>
                   <td>${event.dateEnd.listForm}</td>
                   <td>${event.type}</td>
                   <td>${event.person.name}</td>
                   <td>${event.subject}</td>
                   <td>
                       <a href="/events/${client.id}/editEvent/${event.id}?page=client"> Edit </a>
                        <#if isViewOnly==false>
                            <a href="/events/${client.id}/delEvent/${event.id}?page=client"> Delete </a>
                        </#if>
                   </td>
               </tr>
            </#list>
    </table>
    <#if isViewOnly==false>
        <a class="btn btn-primary btn-sm" href="/events/${client.id}/editEvent/0?page=client">Добавить</a>
    </#if>
</div>