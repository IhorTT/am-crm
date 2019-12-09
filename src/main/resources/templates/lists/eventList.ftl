<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">

<@c.page>

<div>
    <a class="btn btn-primary btn-sm ml-2 mt-2" data-toggle="collapse" href="#collapseFilter" role="button"
       aria-expanded="false"
       aria-controls="collapseFilter">
        Отбор и сортировка
    </a>

    <div class="collapse  ml-1 mt-3" id="collapseFilter">

        <form method="get" action="/events">
            <div class="form-inline ml-3">
                <h6 class="mr-2"> Отборы: </h6>

                <h6> Вид: </h6>
                <select class="custom-select ml-2" name="type">
                    <option value=""></option>
                    <#list types as type>
                        <#if type == selectedType>
                            <option selected value=${type}>${type.label}</option>
                        <#else>
                            <option value=${type}>${type.label}</option>
                        </#if>
                    </#list>
                </select>

                <h6 class="ml-2"> Состояние: </h6>
                <select class="custom-select ml-2" name="state">
                    <option value=""></option>
                    <#list states as state>
                        <#if state ==  selectedState>
                            <option selected value=${state}>${state.label}</option>
                        <#else>
                            <option value=${state}>${state.label}</option>
                        </#if>
                    </#list>
                </select>

                <h6 class="ml-2"> Клиент: </h6>
                <select class="custom-select ml-2" name="clientId">
                    <option value=""></option>
                    <#list clients as client>
                        <#if client ==  selectedClient>
                            <option selected value=${client.id}>${client.name}</option>
                        <#else>
                            <option value=${client.id}>${client.name}</option>
                        </#if>
                    </#list>
                </select>

                <h6 class="ml-2"> Ответственный: </h6>
                <select class="custom-select ml-2" name="managerId" <#if isOwnObjects>disabled</#if>>
                    <option value=""></option>
                    <#list managers as manager>
                        <#if manager ==  selectedManager>
                            <option selected value=${manager.id}>${manager.name}</option>
                        <#else>
                            <option value=${manager.id}>${manager.name}</option>
                        </#if>
                    </#list>
                </select>


            </div>

            <div class="form-inline ml-3 mt-2">
                <h6 class="ml-2"> Сортировка: </h6>

                <select class="custom-select ml-2" name="sort">
                    <option value=""></option>
                    <#list sort as kindSort>
                        <#if kindSort ==  selectedSort>
                            <option selected value=${kindSort}>${kindSort.label}</option>
                        <#else>
                            <option value=${kindSort}>${kindSort.label}</option>
                        </#if>
                    </#list>
                </select>
                <button type="submit" class="btn btn-primary btn-sm ml-2">Применить</button>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        </form>

    </div>
</div>

   <div class="form-row col-md-10 mt-2 tablescrollable">
       <table class="table table-hover table-condensed">
           <thead class="thead-light">
           <tr>
               <th scope="col">Номер</th>
               <th scope="col">Дата</th>
               <th scope="col">Вид</th>
               <th scope="col">Состояние</th>
               <th scope="col">Начало</th>
               <th scope="col">Окончание</th>
               <th scope="col">Клиент</th>
               <th scope="col">Контактное лицо</th>
               <th scope="col">Тема</th>
               <th scope="col">Управление</th>
           </tr>
           </thead>
            <#list events as event>
               <tr>
                   <td>${event.number}</td>
                   <td>${event.date.listForm}</td>
                   <td>${event.type}</td>
                   <td>${event.state}</td>
                   <td>${event.dateStart.listForm}</td>
                   <td>${event.dateEnd.listForm}</td>
                   <td>${event.client.name}</td>
                   <td>${event.person.name}</td>
                   <td>${event.subject}</td>
                   <td>
                       <a href="/events/${event.client.id}/editEvent/${event.id}?page=events"> Edit </a>
                        <#if isViewOnly==false>
                        <a href="/events/${event.client.id}/delEvent/${event.id}?page=events"> Delete </a>
                        </#if>

                   </td>
               </tr>
            </#list>
       </table>

   </div>

    <#if isViewOnly==false>
    <div>
        <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapse" role="button" aria-expanded="false"
           aria-controls="collapse">
            Добавить
        </a>
        <div class="collapse  ml-1 mt-3" id="collapse">

            <form method="post" class="form-inline ml-3" action="/events/new">
                <div> Клиент:</div>

                <select class="custom-select ml-2" id="client" name="client_id">
                    <option value=""></option>
                <#list clients as client>
                    <option value=${client.id}>${client.name}</option>
                </#list>
                </select>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" id="btnAdd" class="btn btn-primary btn-sm ml-1">Ок</button>
            </form>

        </div>
    </div>
    </#if>
</@c.page>
