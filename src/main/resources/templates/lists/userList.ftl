<div class="form-row col-md-8 tablescrollable">
    <table class="table table-hover table-condensed">
        <thead class="thead-light">
        <tr>
            <th>Наименование</th>
            <th>Роли</th>
            <th>Управление</th>
        </tr>
        </thead>

    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td>
            <#list user.roles as role>
                ${role.label}
            <#sep>,
            </#list>
        </td>
        <td>
            <a href="/user/${user.id}/edit">Edit</a>
            <a href="/user/${user.id}/delete">Delete</a>
        </td>
    </tr>
    </#list>
    </table>
    <a class="btn btn-primary btn-sm" href="/user/0/edit/">Добавить</a>
</div>