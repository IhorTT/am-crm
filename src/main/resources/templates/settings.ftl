<#import "parts/common.ftl" as c>
<#import "lists/thesaurus.ftl" as t>
<#include "parts/security.ftl">

<@c.page>
 <div class="">
     <div class="nav nav-tabs" id="nav-tab" role="tablist">
         <a class="nav-item nav-link active" id="nav-typeEq-tab" data-toggle="tab" href="#nav-typeEq" role="tab"
            aria-controls="nav-typeEq" aria-selected="false">Виды техники</a>
         <a class="nav-item nav-link" id="nav-manuf-tab" data-toggle="tab" href="#nav-manuf" role="tab"
            aria-controls="nav-manuf" aria-selected="false">Производители</a>
         <a class="nav-item nav-link" id="nav-pos-tab" data-toggle="tab" href="#nav-pos" role="tab"
            aria-controls="nav-pos" aria-selected="false">Должности</a>
    <#if isAdmin>
        <a class="nav-item nav-link" id="nav-users-tab" data-toggle="tab" href="#nav-users" role="tab"
           aria-controls="nav-users" aria-selected="false">Пользователи</a>
    </#if>
     </div>

     <div class="tab-content" id="nav-tabContent">
         <div class="tab-pane fade show active" id="nav-typeEq" role="tabpanel" aria-labelledby="nav-typeEq-tab">
            <@t.showThesarus caption = "Вид" list = typeEquipment nameThes="TypeOfAgriculturalMachinery"
            tab = "typeEq"/>
         </div>

         <div class="tab-pane fade" id="nav-manuf" role="tabpanel" aria-labelledby="nav-manuf-tab">
            <@t.showThesarus caption = "Наименование" list = manufacturers nameThes="Manufacturer"
            tab = "manuf" />
         </div>

         <div class="tab-pane fade" id="nav-pos" role="tabpanel" aria-labelledby="nav-pos-tab">
            <@t.showThesarus caption = "Наименование" list = positions nameThes="Position"
            tab = "pos"/>
         </div>
    <#if isAdmin>
        <div class="tab-pane fade" id="nav-users" role="tabpanel" aria-labelledby="nav-users-tab">
            <#include "lists/userList.ftl">
        </div>
    </#if>

     </div>
 </div>

</@c.page>

<script type="text/javascript">
    $('.nav-tabs a[href="#nav-${tab}"]').tab('show')
</script>