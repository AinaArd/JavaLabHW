<html>
<head>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Users
    </div>
    <table>
        <#list users as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.login}</td>
            </tr>
        </#list>
    </table>

    <p>
    <form method="get">
        Enter user name<br>
        <input class="input-field" type="text" id="userName" name="userName">
    </form>
    </p>

    <form method="post">
        <input type="submit" value="New user">
    </form>
</div>
</body>
</html>
