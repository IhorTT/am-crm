<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">

<@c.page>

<h6> Клиент: ${client.name} </h6>

    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-item nav-link active" id="nav-main-tab" data-toggle="tab" href="#nav-main" role="tab"
           aria-controls="nav-main" aria-selected="false">Общие</a>
        <a class="nav-item nav-link" id="nav-ci-tab" data-toggle="tab" href="#nav-ci" role="tab"
           aria-controls="nav-ci" aria-selected="false">Контактная информация</a>
        <a class="nav-item nav-link" id="nav-cp-tab" data-toggle="tab" href="#nav-cp" role="tab"
           aria-controls="nav-cp" aria-selected="false">Контактные лица</a>
        <a class="nav-item nav-link" id="nav-events-tab" data-toggle="tab" href="#nav-events" role="tab"
           aria-controls="nav-events" aria-selected="false">События</a>
        <a class="nav-item nav-link" id="nav-ep-tab" data-toggle="tab" href="#nav-ep" role="tab"
           aria-controls="nav-ep" aria-selected="false">Парк техники</a>
        <a class="nav-item nav-link" id="nav-rp-tab" data-toggle="tab" href="#nav-rp" role="tab"
           aria-controls="nav-rp" aria-selected="false">Потребности</a>
    </div>

    <div class="tab-content" id="nav-tabContent">

        <div class="tab-pane fade show active" id="nav-main" role="tabpanel" aria-labelledby="nav-main-tab">
               <#include "../parts/clientHead.ftl">
        </div>

        <div class="tab-pane fade" id="nav-ci" role="tabpanel" aria-labelledby="nav-ci-tab">
            <#if client.id == 0>
                <h5> Для заполнения вкладок необходимо сохранить клиента</h5>
            <#else>
                <#include "../parts/clientContactInfo.ftl">
            </#if>
        </div>

        <div class="tab-pane fade" id="nav-cp" role="tabpanel" aria-labelledby="nav-cp-tab">
            <#if client.id ==0>
                <h5> Для заполнения вкладок необходимо сохранить клиента</h5>
            <#else>
                <#include "../parts/clientContactPersons.ftl">
            </#if>
        </div>

        <div class="tab-pane fade" id="nav-events" role="tabpanel" aria-labelledby="nav-events-tab">
            <#if client.id ==0>
                <h5> Для заполнения вкладок необходимо сохранить клиента</h5>
            <#else>
                <#include "../parts/clientEvents.ftl">
            </#if>
        </div>

        <div class="tab-pane fade" id="nav-ep" role="tabpanel" aria-labelledby="nav-ep-tab">
            <#if client.id ==0>
                <h5> Для заполнения вкладок необходимо сохранить клиента</h5>
            <#else>
                <#include "../parts/clientParkEquipment.ftl">
            </#if>
        </div>

        <div class="tab-pane fade" id="nav-rp" role="tabpanel" aria-labelledby="nav-rp-tab">
            <#if client.id ==0>
                <h5> Для заполнения вкладок необходимо сохранить клиента</h5>
            <#else>
                <#include "../parts/clientParkRequirement.ftl">
            </#if>
        </div>

    </div>

</@c.page>

<script type="text/javascript">
    $('.nav-tabs a[href="#nav-${tabCl}"]').tab('show')
</script>
