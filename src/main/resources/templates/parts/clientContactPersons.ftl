<div class="form-row col-md-6">
    <div class="tablescrollable">
        <table class="table table-hover table-condensed">
            <thead class="thead-light">
            <tr>
                <th scope="col">Наименование</th>
                <th scope="col">Должность</th>
                <th scope="col">Телефон</th>
                <th scope="col">e-mail</th>
                <th scope="col">Управление</th>
            </tr>
            </thead>

            <#list client.persons as cp>
                <tr>
                    <td>${cp.name}</td>
                    <td>${cp.position.name}</td>
                    <td>${cp.phone}</td>
                    <td>${cp.email}</td>
                    <td>
                        <a href="/persons/${client.id}/editCP/${cp.id}?page=client"> Edit </a>
                        <#if isViewOnly==false>
                            <a href="/persons/${client.id}/delCP/${cp.id}?page=client"> Delete </a>
                        </#if>
                    </td>
                </tr>
            </#list>

        </table>
        <#if isViewOnly==false>
            <a class="btn btn-primary btn-sm" href="/persons/${client.id}/editCP/0?page=client">Добавить</a>
        </#if>
    </div>
</div>