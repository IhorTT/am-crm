<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">

<@c.page>

<form class="" action="/events/editEvent" method="post">
    <div class="ml-2">
        <h6> Событие: ${event.number} от  ${event.date.listForm} </h6>
    </div>
    <div class="ml-3">
        <div class="row ">
            <div class="form-inline pt-2">
                <label class="mr-4 ml-1" for="number">Номер:</label>
                <input type="text" class="form-control  " id="number" name="number" value="${event.number}">
                <label class="mr-2 ml-2" for="date">от:</label>
                <input type="datetime-local" class="form-control col-lg-5" id="date" name="date" value="${event.date.editForm}">
            </div>
        </div>
        <div class="row ">
            <div class="form-inline pt-2">
                <label class="mr-1 ml-1" for="dateStart">Период с:</label>
                <input type="datetime-local" class="form-control col-lg-4" id="dateStart" name="dateStart"
                       value="${event.dateStart.editForm}">
                <label class="mr-1 ml-1" for="dateEnd">по:</label>
                <input type="datetime-local" class="form-control ml-1 " id="dateEnd" name="dateEnd"
                       value="${event.dateEnd.editForm}">
            </div>
        </div>

        <div class="row">
            <div class="form-inline pt-2">
                <label for="state">Состояние: </label>
                <select class="custom-select form-control col-lg-4 " id="state" name="state">
                    <option value=""></option>
                <#list states as state>
                    <#if state.label == event.state>
                        <option selected value=${state}>${state.label}</option>
                    <#else>
                        <option value=${state}>${state.label}</option>
                    </#if>
                </#list>
                </select>

                <label class="ml-4 mr-1" for="type">Вид:</label>
                <select class="custom-select form-control col-lg-4 " id="type" name="type">
                    <option value=""></option>
                <#list types as type>
                    <#if type.label == event.type>
                        <option selected value=${type}>${type.label}</option>
                    <#else>
                        <option value=${type}>${type.label}</option>
                    </#if>
                </#list>
                </select>


            </div>
        </div>

        <div class="form-inline row pt-2">
            <label class="mr-4" for="client">Клиент:</label>
            <input type="text" class="form-control col-lg-3" id="client" name="client" value="${event.client.name}"
                   disabled>
        </div>

        <div class="form-inline row pt-2">
            <label class="mr-4 for=" subject">Тема:</label>
            <input type="text" class="form-control col-lg-3 ml-3" id="subject" name="subject" value="${event.subject}"
                   placeholder="Тема">
        </div>

        <div class="row pt-2">
            <label class="mr-1" for="content">Описание:</label>
            <textarea class="form-control col-lg-3" id="content" rows="2" name="content"></textarea>
        </div>

        <div class="form-inline row pt-2">
            <label class="mr-2" for="person">Контактное лицо: </label>
            <select class="custom-select col-lg-2" id="person" name="personId">
                <option value=""></option>
                <#list event.client.persons as person>
                    <#if person == event.person>
                        <option selected value=${person.id}>${person.name}</option>
                    <#else>
                        <option value=${person.id}>${person.name}</option>
                    </#if>
                </#list>
            </select>
        </div>

        <div class="form-inline row pt-2">
            <label class="mr-4" for="manager">Отвественный:</label>
            <select class="custom-select col-lg-2 ml-1" id="manager" name="managerId">
                <option value=""></option>
                <#list managers as manager>
                    <#if manager == event.manager>
                        <option selected value=${manager.id}>${manager.name}</option>
                    <#else>
                        <option value=${manager.id}>${manager.name}</option>
                    </#if>
                </#list>
            </select>
        </div>

        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <input type="hidden" name="id" value="${event.id}">
        <input type="hidden" name="client_id" value="${event.client.id}">
        <input type="hidden" name="page" value="${page}">

    </div>

    <div class="pt-2">
    <button type="submit" class="btn btn-primary btn-sm" name="btn" id="btnOk" value="btnOk">Ок</button>
    <button type="submit" class="btn btn-primary btn-sm" name="btn" value="btnCancel">Отмена</button>
    </div>
</form>

</@c.page>

<#if isViewOnly>
    <script type="text/javascript">
        document.getElementById("number").setAttribute('readonly', 'readonly')
        document.getElementById("date").setAttribute('readonly', 'readonly')
        document.getElementById("dateStart").setAttribute('readonly', 'readonly')
        document.getElementById("dateEnd").setAttribute('readonly', 'readonly')
        document.getElementById("state").setAttribute('disabled', 'disabled')
        document.getElementById("type").setAttribute('disabled', 'disabled')
        document.getElementById("client").setAttribute('disabled', 'disabled')
        document.getElementById("subject").setAttribute('readonly', 'readonly')
        document.getElementById("content").setAttribute('readonly', 'readonly')
        document.getElementById("person").setAttribute('disabled', 'disabled')
        document.getElementById("manager").setAttribute('disabled', 'disabled')
        document.getElementById("btnOk").setAttribute('disabled', 'disabled')
    </script>
</#if>
