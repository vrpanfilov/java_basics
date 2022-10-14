let editingId = 0;
let todoCount = 0;

const appendTodo = function (todoId) {
    showForm(0);
    let todo = {};
    todo.id = todoId;
    let dataArray = $('#todo-form form').serializeArray();
    for (i in dataArray) {
        todo[dataArray[i]['name']] = dataArray[i]['value'];
    }
    let todoCode =
        '<button class="delete-todo" data-id="' + todo.id + '">X</button>' +
        '<a href="#" class="todo-link" ' +
            'data-id="' + todo.id + '">' + todo.content + '</a>';
    $('#todo-list')
        .append('<div>' + todoCode + '</div>');
    $('h1 span').text(++todoCount);
};

const deleteTodo = function (button) {
    button.parent().remove();
    $('h1 span').text(--todoCount);
}

const deleteAll = function () {
    $('div#todo-list').empty();
    todoCount = 0;
    $('h1 span').text(todoCount);
}

const addNewTodo = function () {
    let data = $('#todo-form form').serialize();
    $.ajax({
        method: "POST",
        url: '/todos/',
        data: data,
        success: function (response) {
            appendTodo(response);
        }
    });
}

const saveEditedTodo = function () {
    let data = $('#todo-form form').serialize();
    $.ajax({
        method: "PUT",
        url: '/todos/' + editingId,
        data: data,
        success: function (response) {
            var newContent = $('input[name="content"]').val();
            $('a[data-id=' + editingId + ']').text(newContent);
        }
    });
    showForm(0);
}

const showForm = function(toShow) {
    if (toShow) {
        $('#todo-form').css('display', 'flex');
    } else {
        $('#todo-form').css('display', 'none');
    }
}

$(function () {
    todoCount = $('h1 span').text();

    //Show adding todo form
    $('#show-add-todo-form').click(function () {
        // Clear all inputs
        $('#todo-form form').find('input:text').val('');
        editingId = 0;
        $('#todo-form form h2').text('Добавление дела');
        showForm(1);
    });

    //Closing adding todo form
    $('#todo-form').click(function (event) {
        if (event.target === this) {
            showForm(0);
        }
    });

    //Editting todo
    $(document).on('click', '.todo-link', function () {
        let link = $(this);
        let todoId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/todos/' + todoId,
            success: function (response) {
                editingId = response.id;
                $('input[name="executor"]').val(response.executor);
                $('input[name="content"]').val(response.content);
                $('input[name="startDate"]').val(response.startDate);
                $('input[name="endDate"]').val(response.endDate);

                $('#todo-form form h2').text('Изменение дела');
                showForm(1);
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    //Adding todo
    $('#save-todo').click(function () {
        if (editingId == 0) {
            addNewTodo();
        } else {
            saveEditedTodo();
        }
        return false;
    });

    // Deleting todo
    $(document).on('click', '.delete-todo', function () {
        let button = $(this);
        let todoId = button.data('id');
        $.ajax({
            method: "DELETE",
            url: '/todos/' + todoId,
            success: function (response) {
                deleteTodo(button);
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    // Deleting all
    $(document).on('click', '#delete-all', function () {
        $.ajax({
            method: "DELETE",
            url: '/todos/',
            success: function (response) {
                deleteAll();
            },
            error: function (response) {
                alert('Что-то не так!');
            }
        });
        return false;
    });
});