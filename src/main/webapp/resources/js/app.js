function resultValidate($field, errors){
    if (errors){
        var error = errors[$field[0].id];
        if (error) {
            $field.closest(".form-group").addClass("has-error").find(".bg-danger").removeClass("hidden").addClass("show").text(error);
            return;
        }
    }
    $field.closest(".form-group").removeClass("has-error").find(".bg-danger").removeClass("show").addClass("hidden");
};

function errorShow() {
    $("#page-error").removeClass("hidden");
};
function successShow() {
    $("#page-success").removeClass("hidden");
    setTimeout(function () {
        $("#page-success").addClass("hidden");
    }, 2500);
};
function save(){
    $("#ajax-form").ajaxForm({
        dataType: "json",
        type: "POST",
        success:function(data) {
            $("#ajax-form [data-field]").each(function() {
                var $this = $(this);
                resultValidate($this, data.errors);
                if (!data.errors){
                    $this.val("").attr("checked", false);
                }
            });

            if(!data.errors){
                successShow();
                $("#order-table").empty().append(data.content);
            }
        },
        error: errorShow
    }).submit();
};

function publish(obj, id) {
    $.ajax("/letter/publish",
        {
            data: { id: id },
            dataType: "json",
            type: "POST",
            success: function (data) {
                $(obj).attr("checked", true).attr("disabled", true);
                successShow();
            },
            error: function () {
                errorShow();
                $(obj).attr("checked", false);
            }
        });
};