if ($.validator != null) {
    $.extend($.validator.messages, {
        required: '值不能为空',
        email: '请输入正确格式的电子邮件',
        url: '请输入合法的网址,必须以“http://”开头',
        date: '请输入合法的日期',
        dateISO: '请输入合法的日期 (ISO)',
        number: '请输入合法的数字',
        digits: '只能输入整数',
        equalTo: "请再次输入相同的值",
        maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
        minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: $.validator.format("请输入一个最大为 {0} 的值"),
        min: $.validator.format("请输入一个最小为 {0} 的值")
    });
    $.validator.setDefaults({
        showErrors: function (map, errorlist) {
            var currentelements = this.currentElements;
            currentelements.removeAttr("data-original-title").removeClass("vali-error");
            $(errorlist).each(function (i, v) {
                $(v.element).attr("data-original-title", v.message).addClass("vali-error");
                if (typeof($(v.element).attr("aria-describedby")) != "undefined"){
                    $("#" + $(v.element).attr("aria-describedby")).find(".tooltip-inner").html(v.message);
                }
                else if($(v.element).is(":focus")){
                    $(v.element).tooltip("show");
                }

            })
            $(this.successList).each(function (i, v) {
                $(v).tooltip("hide");
            })
        },
        hideErrors: function () {
            $(this.settings.errorElement + "[generated='true']").attr("class", this.settings.validClass).html(" *");
            //this.addWrapper(this.toHide).hide();
        },
    });
    jQuery.validator.addMethod("mobile", function (value, element) {
        return this.optional(element) || value.isMobile();
    }, "手机号码格式错误");
}