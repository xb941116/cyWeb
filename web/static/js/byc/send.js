// ==================================================
// ==== Send v2.0.0.1 ====
// 基于jQuery v1.3.2，在旧版Send基础上扩展，包括以下方法集合：
// - JS原型方法扩展： String/Number/Array/Date
// - jQuery方法扩展: $.browser/$.fn
// - Send旧版保留方法: flash/DOM/DSS/cookie
// - Send方法库： core/json/base/page/login
// ==================================================
window.Spp = {};
/* String 原型方法扩展 */
(function ($) {
    if (!$) return;
    $.extend(String.prototype, {
        _toBoolean: function () {
            return (this == 'false' || this == '' || this == '0') ? false : true;
        },
        _toNumber: function () {
            return (!isNaN(this)) ? Number(this) : this.toString();
        },
        _toRealValue: function () {
            return (this == 'true' || this == 'false') ? this._toBoolean() : this._toNumber();
        },
        trim: function () {
            return this.replace(/(^\s*)|(\s*$)/g, '');
        },
        ltrim: function () {
            return this.replace(/(^\s*)/g, '');
        },
        rtrim: function () {
            return this.replace(/(\s*$)/g, '');
        },
        trimAll: function () {
            return this.replace(/\s/g, '');
        },
        trimStart: function (trimStr) {
            if (!trimStr) {
                return this;
            }
            var temp = this;
            while (true) {
                if (temp.substr(0, trimStr.length) != trimStr) {
                    break;
                }
                temp = temp.substr(trimStr.length);
            }
            return temp;
        },
        trimEnd: function (trimStr) {
            if (!trimStr) {
                return this;
            }
            var temp = this;
            while (true) {
                if (temp.substr(temp.length - trimStr.length, trimStr.length) != trimStr) {
                    break;
                }
                temp = temp.substr(0, temp.length - trimStr.length);
            }
            return temp;
        },
        left: function (len) {
            return this.substring(0, len);
        },
        right: function (len) {
            return (this.length <= len) ? this.toString() : this.substring(this.length - len, this.length);
        },
        reverse: function () {
            return this.split('').reverse().join('');
        },
        startWith: function (start, noCase) {
            return !(noCase ? this.toLowerCase().indexOf(start.toLowerCase()) : this.indexOf(start));
        },
        endWith: function (end, noCase) {
            return noCase ? (new RegExp(end.toLowerCase() + "$").test(this.toLowerCase().trim())) : (new RegExp(end + "$").test(this.trim()));
        },
        sliceAfter: function (str) {
            return (this.indexOf(str) >= 0) ? this.substring(this.indexOf(str) + str.length, this.length) : '';
        },
        sliceBefore: function (str) {
            return (this.indexOf(str) >= 0) ? this.substring(0, this.indexOf(str)) : this;
        },
        getByteLength: function () {
            return this.replace(/[^\x00-\xff]/ig, 'xx').length;
        },
        subByte: function (len, s) {
            if (len < 0 || this.getByteLength() <= len) {
                return this.toString();
            }
            var str = this;
            str = str.substr(0, len).replace(/([^\x00-\xff])/g, "\x241 ").substr(0, len).replace(/[^\x00-\xff]$/, "").replace(/([^\x00-\xff]) /g, "\x241");
            return str + (s || '');
        },
        encodeURI: function (type) {
            var etype = type || 'utf',
                efn = (etype == 'uni') ? escape : encodeURIComponent;
            return efn(this);
        },
        decodeURI: function (type) {
            var dtype = type || 'utf',
                dfn = (dtype == 'uni') ? unescape : decodeURIComponent;
            try {
                var os = this.toString(),
                    ns = dfn(os);
                while (os != ns) {
                    os = ns;
                    ns = dfn(os);
                }
                return os;
            } catch (e) {
                try {
                    var os = this.toString(),
                        ns = unescape(os);
                    while (os != ns) {
                        os = ns;
                        ns = unescape(os);
                    }
                    return os;
                }
                catch (e) {
                    return this.toString();
                }
            }
        },
        textToHtml: function () {
            return this.replace(/</ig, '&lt;').replace(/>/ig, '&gt;').replace(/\r\n/ig, '<br>').replace(/\n/ig, '<br>');
        },
        htmlToText: function () {
            return this.replace(/<br>/ig, '\r\n');
        },
        htmlEncode: function () {
            var text = this,
                re = {
                    '<': '&lt;',
                    '>': '&gt;',
                    '&': '&amp;',
                    '"': '&quot;'
                };
            for (i in re) {
                text = text.replace(new RegExp(i, 'g'), re[i]);
            }
            return text;
        },
        htmlDecode: function () {
            var text = this,
                re = {
                    '&lt;': '<',
                    '&gt;': '>',
                    '&amp;': '&',
                    '&quot;': '"'
                };
            for (i in re) {
                text = text.replace(new RegExp(i, 'g'), re[i]);
            }
            return text;
        },
        stripHtml: function () {
            return this.replace(/(<\/?[^>\/]*)\/?>/ig, '');
        },
        stripScript: function () {
            return this.replace(/<script(.|\n)*\/script>\s*/ig, '').replace(/on[a-z]*?\s*?=".*?"/ig, '');
        },
        replaceAll: function (os, ns) {
            return this.replace(new RegExp(os, "gm"), ns);
        },
        escapeReg: function () {
            return this.replace(new RegExp("([.*+?^=!:\x24{}()|[\\]\/\\\\])", "g"), '\\\x241');
        },
        addQueryValue: function (name, value) {
            var url = this.getPathName();
            var param = this.getQueryJson();
            param[name] = value;
            return url + '?' + $.param(param);
        },
        getQueryValue: function (name) {
            var reg = new RegExp("(^|&|\\?|#)" + name.escapeReg() + "=([^&]*)(&|\x24)", "");
            var match = this.match(reg);
            return (match) ? match[2].replace(/\+/g, '%20').decodeURI() : '';
        },
        removeQueryValue: function (name) {
            if (this.indexOf('?') <= 0) return this;
            var url = this.getPathName();
            var param = this.getQueryJson();
            if (param[name])
                delete param[name];
            return (url + '?' + $.param(param)).trimEnd("?");
        },
        getQueryJson: function () {
            var query = this;
            if (query.indexOf('?') < 0 && query.indexOf('&') < 0) return {};
            if (this.substring(this.indexOf('?') + 1) == "") return {}
            if (query.indexOf('?') >= 0)// return {};
                query = this.substr(this.indexOf('?') + 1);
            var params = query.split('&'),
                len = params.length,
                result = {},
                key,
                value,
                item,
                param;
            for (var i = 0; i < len; i++) {
                param = params[i].split('=');
                key = param[0].trim();
                value = param[1].decodeURI();
                item = result[key];
                if ('undefined' == typeof item) {
                    result[key] = value;
                } else if (Object.prototype.toString.call(item) == '[object Array]') {
                    item.push(value);
                } else {
                    result[key] = [item, value];
                }
            }
            return result;
        },
        getDomain: function () {
            if (this.startWith('http://')) return this.split('/')[2];
            return '';
        },
        getPathName: function () {
            return (this.lastIndexOf('?') == -1) ? this.toString() : this.substring(0, this.lastIndexOf('?'));
        },
        getFilePath: function () {
            return this.substring(0, this.lastIndexOf('/') + 1);
        },
        getFileName: function () {
            return this.substring(this.lastIndexOf('/') + 1);
        },
        getFileExt: function () {
            return this.substring(this.lastIndexOf('.') + 1);
        },
        parseDate: function () {
            return (new Date()).parse(this.toString());
        },
        parseJSON: function () {
            return (new Function("return " + this.toString()))();
        },
        parseAttrJSON: function () {
            var d = {},
                a = this.toString().sliceBefore('}').sliceAfter('{').split(',');
            for (var i = 0; i < a.length; i++) {
                if (a[i].trim() == '' || a[i].indexOf(':') < 1) continue;
                var item = a[i].sliceBefore(':').trim(),
                    val = a[i].sliceAfter(':').trim();
                if (item != '' && val != '') d[item.toCamelCase()] = val._toRealValue();
            }
            return d;
        },
        parseFromJSON: function () {
            var str = this;
            str = str.replace(/&/g, "','");
            str = str.replace(/=/g, "':'");
            str = "{'" + str + "'}";
            return (new Function("return " + str))();
            //var d = {},
            //a = this.toString().split('&');
            //for (var i = 0; i < a.length; i++) {
            //    if (a[i].trim() == '' || a[i].indexOf('=') < 1) continue;
            //    var item = a[i].sliceBefore('=').trim(),
            //    val = a[i].sliceAfter('=').trim();
            //    if (item != '' && val != '') d[item.toCamelCase()] = val._toRealValue();
            //}
            //return d;
        },
        _pad: function (width, ch, side) {
            var str = [side ? '' : this, side ? this : ''];
            while (str[side].length < (width ? width : 0) && (str[side] = str[1] + (ch || ' ') + str[0]));
            return str[side];
        },
        padLeft: function (width, ch) {
            if (this.length >= width) return this.toString();
            return this._pad(width, ch, 0);
        },
        padRight: function (width, ch) {
            if (this.length >= width) return this.toString();
            return this._pad(width, ch, 1);
        },
        toHalfWidth: function () {
            return this.replace(/[\uFF01-\uFF5E]/g,
                function (c) {
                    return String.fromCharCode(c.charCodeAt(0) - 65248);
                }).replace(/\u3000/g, " ");
        },
        toCamelCase: function () {
            if (this.indexOf('-') < 0 && this.indexOf('_') < 0) {
                return this.toString();
            }
            return this.replace(/[-_][^-_]/g,
                function (match) {
                    return match.charAt(1).toUpperCase();
                });
        },
        format: function () {
            var result = this;
            if (arguments.length > 0) {
                var parameters = (arguments.length == 1 && $.isArray(arguments[0])) ? arguments[0] : $.makeArray(arguments);
                $.each(parameters,
                    function (i, n) {
                        result = result.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                    });
            }
            return result;
        },
        substitute: function (data) {
            if (data && typeof (data) == 'object') {
                return this.replace(/\{([^{}]+)\}/g,
                    function (match, key) {
                        var value = data[key];
                        return (value !== undefined) ? '' + value : '';
                    });
            } else {
                return this.toString();
            }
        },
        extractValues: function (pattern, options) {
            var str = this.toString();
            options = options || {};
            var delimiters = options.delimiters || ["{", "}"];
            var lowercase = options.lowercase;
            var whitespace = options.whitespace;
            var special_chars_regex = /[\\\^\$\*\+\.\?\(\)]/g;
            var token_regex = new RegExp(delimiters[0] + "([^" + delimiters.join("") + "\t\r\n]+)" + delimiters[1], "g");
            var tokens = pattern.match(token_regex);
            var pattern_regex = new RegExp(pattern.replace(special_chars_regex, "\\$&").replace(token_regex, "(\.+)"));
            if (lowercase) {
                str = str.toLowerCase();
            }
            if (whitespace) {
                str = str.replace(/\s+/g,
                    function (match) {
                        var whitespace_str = "";
                        for (var i = 0; i < whitespace; i++) {
                            whitespace_str = whitespace_str + match.charAt(0);
                        }
                        ;
                        return whitespace_str;
                    });
            }
            ;
            var matches = str.match(pattern_regex);
            if (!matches) {
                return null;
            }
            matches = matches.splice(1);
            var output = {};
            for (var i = 0; i < tokens.length; i++) {
                output[tokens[i].replace(new RegExp(delimiters[0] + "|" + delimiters[1], "g"), "")] = matches[i];
            }
            return output;
        },
        toEAN13: function (pre) {
            var len = 12 - pre.length;
            var str = pre + ((this.length >= len) ? this.left(len) : this.padLeft(len, '0'));
            var a = 0,
                b = 0,
                c = 0,
                d = str.reverse();
            for (var i = 0; i < d.length; i++) {
                if (i % 2) {
                    b += parseInt(d.charAt(i));
                } else {
                    a += parseInt(d.charAt(i));
                }
            }
            if ((a * 3 + b) % 10) {
                c = 10 - (a * 3 + b) % 10;
            }
            return str + c;
        },
        formatToDSS: function () {
            var m = this.match(/^http(?:s)?:\/\/([^:\/]*)(:\d+)?([^\?]*)/);
            var a = '';
            if (m[1].match(/^[\d\.]*$/)) {
                a = '/ip/' + m[1];
            } else {
                var b = m[1].split('.');
                for (var i = b.length; i > 0; i--) {
                    a += '/' + b[i - 1];
                }
            }
            if (m[2]) {
                a += '/' + m[2];
            }
            return a + m[3];
        },
        formatToDSSID: function () {
            return this.formatToDSS().replace(/\//g, '_');
        },
        toMapObject: function (sep) {
            var sep = sep || '/';
            var s = this.split(sep);
            var d = {};
            var o = function (a, b, c) {
                if (c < b.length) {
                    if (!a[b[c]]) {
                        a[b[c]] = {};
                    }
                    d = a[b[c]];
                    o(a[b[c]], b, c + 1);
                }
            };
            o(window, s, 1);
            return d;
        }
    });
})(jQuery);
/* String 数据校验相关 */
(function ($) {
    if (!$) return;
    $.extend(String.prototype, {
        isIP: function () {
            var re = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
            return re.test(this.trim());
        },
        isUrl: function () {
            // return (new RegExp(/^http[s]?:\/\/([\w-]+\.)+[\w-]+([\w-./ ? %&=] * ) ? $ / i).test(this.trim()));
        },
        isURL: function () {
            return this.isUrl();
        },
        isDate: function () {
            var result = this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if (result == null) return false;
            var d = new Date(result[1], result[3] - 1, result[4]);
            return (d.getFullYear() == result[1] && d.getMonth() + 1 == result[3] && d.getDate() == result[4]);
        },
        isTime: function () {
            var result = this.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
            if (result == null) return false;
            if (result[1] > 24 || result[3] > 60 || result[4] > 60) return false;
            return true;
        },
        isDateTime: function () {
            var result = this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
            if (result == null) return false;
            var d = new Date(result[1], result[3] - 1, result[4], result[5], result[6], result[7]);
            return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4] && d.getHours() == result[5] && d.getMinutes() == result[6] && d.getSeconds() == result[7]);
        },
        isInteger: function () {
            return (new RegExp(/^(-|\+)?\d+$/).test(this.trim()));
        },
        isPositiveInteger: function () {
            return (new RegExp(/^\d+$/).test(this.trim())) && parseInt(this) > 0;
        },
        isNegativeInteger: function () {
            return (new RegExp(/^-\d+$/).test(this.trim()));
        },
        isNumber: function () {
            return !isNaN(this);
        },
        isRealName: function (str) {
            return /^[A-Za-z \u4E00-\u9FA5]+$/.test(this);
        },
        isLogName: function () {
            return (this.isEmail() || this.isMobile());
        },
        isEmail: function () {
            return (new RegExp(/^([_a-zA-Z\d\-\.])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/).test(this.trim()));
        },
        isMobile: function () {
            return (new RegExp(/^(13|14|15|17|18)\d{9}$/).test(this.trim()));
        },
        isPhone: function () {
            return (new RegExp(/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/).test(this.trim()));
        },
        isAreacode: function () {
            return (new RegExp(/^0\d{2,3}$/).test(this.trim()));
        },
        isPostcode: function () {
            return (new RegExp(/^\d{6}$/).test(this.trim()));
        },
        isLetters: function () {
            return (new RegExp(/^[A-Za-z]+$/).test(this.trim()));
        },
        isDigits: function () {
            return (new RegExp(/^[1-9][0-9]+$/).test(this.trim()));
        },
        isAlphanumeric: function () {
            return (new RegExp(/^[a-zA-Z0-9]+$/).test(this.trim()));
        },
        isValidString: function () {
            return (new RegExp(/^[a-zA-Z0-9\s.\-_]+$/).test(this.trim()));
        },
        isLowerCase: function () {
            return (new RegExp(/^[a-z]+$/).test(this.trim()));
        },
        isUpperCase: function () {
            return (new RegExp(/^[A-Z]+$/).test(this.trim()));
        },
        isChinese: function () {
            return (new RegExp(/^[\u4e00-\u9fa5]+$/).test(this.trim()));
        },
        isIDCard: function () {
            var r15 = new RegExp(/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/);
            var r18 = new RegExp(/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/);
            return (r15.test(this.trim()) || r18.test(this.trim()));
        },
        isCardNo: function (cardType) {
            var cards = {
                "UleCard": {
                    lengths: "16",
                    prefixes: "",
                    checkdigit: true
                },
                "Visa": {
                    lengths: "13,16",
                    prefixes: "4",
                    checkdigit: true
                },
                "MasterCard": {
                    lengths: "16",
                    prefixes: "51,52,53,54,55",
                    checkdigit: true
                },
                "BankCard": {
                    lengths: "16,17,19",
                    prefixes: "3,4,5,6,9",
                    checkdigit: false
                }
            };
            if (!cards[cardType]) return false;
            var cardNo = this.replace(/[\s-]/g, "");
            var cardexp = /^[0-9]{13,19}$/;
            if (cardNo.length == 0 || !cardexp.exec(cardNo)) {
                return false;
            } else {
                cardNo = cardNo.replace(/\D/g, "");
                var modTenValid = true;
                var prefixValid = false;
                var lengthValid = false;
                if (cards[cardType].checkdigit) {
                    var checksum = 0,
                        mychar = "",
                        j = 1,
                        calc;
                    for (i = cardNo.length - 1; i >= 0; i--) {
                        calc = Number(cardNo.charAt(i)) * j;
                        if (calc > 9) {
                            checksum = checksum + 1;
                            calc = calc - 10;
                        }
                        checksum = checksum + calc;
                        if (j == 1) {
                            j = 2
                        } else {
                            j = 1
                        }
                        ;
                    }
                    if (checksum % 10 != 0) modTenValid = false;
                }
                if (cards[cardType].prefixes == '') {
                    prefixValid = true;
                } else {
                    var prefix = cards[cardType].prefixes.split(",");
                    for (i = 0; i < prefix.length; i++) {
                        var exp = new RegExp("^" + prefix[i]);
                        if (exp.test(cardNo)) prefixValid = true;
                    }
                }
                var lengths = cards[cardType].lengths.split(",");
                for (j = 0; j < lengths.length; j++) {
                    if (cardNo.length == lengths[j]) lengthValid = true;
                }
                if (!modTenValid || !prefixValid || !lengthValid) {
                    return false;
                } else {
                    return true;
                }
            }
        },
        isUleCard: function () {
            return this.isCardNo('UleCard');
        },
        isVisa: function () {
            return this.isCardNo('Visa');
        },
        isMasterCard: function () {
            return this.isCardNo('MasterCard');
        },
        isValidEAN: function () {
            var code = this.trim();
            var a = 0,
                b = 0,
                c = parseInt(code.right(1)),
                d = code.left(code.length - 1).reverse();
            for (var i = 0; i < d.length; i++) {
                if (i % 2) {
                    b += parseInt(d.charAt(i));
                } else {
                    a += parseInt(d.charAt(i));
                }
            }
            return ((a * 3 + b + c) % 10) ? false : true;
        },
        isEAN8: function () {
            var code = this.trim();
            return (new RegExp(/^\d{8}$/).test(code)) && code.isValidEAN();
        },
        isEAN12: function () {
            var code = this.trim();
            return (new RegExp(/^\d{12}$/).test(code)) && code.isValidEAN();
        },
        isEAN13: function () {
            var code = this.trim();
            return (new RegExp(/^\d{13}$/).test(code)) && code.isValidEAN();
        },
        isISBN10: function () {
            var code = this.trim();
            if (!new RegExp(/^\d{9}([0-9]|X|x)$/).test(code)) return false;
            var a = 0,
                b = code.right(1),
                c = code.reverse();
            for (var i = 1; i < c.length; i++) {
                a += parseInt(c.charAt(i)) * (i + 1);
            }
            if (b == 'X' || b == 'x') b = 10;
            return ((a + parseInt(b)) % 11) ? false : true;
        },
        isISBN: function () {
            return this.isEAN13();
        },
        isEANCode: function () {
            return this.isEAN8() || this.isEAN12() || this.isEAN13() || this.isISBN10();
        },
        //是否存在指定函数
        isFunction: function () {
            try {
                if (typeof(eval(this.toString())) == "function") {
                    return true;
                }
            } catch (e) {
            }
            return false;
        }
    });
})(jQuery);
/* Number 原型方法扩展 */
(function ($) {
    if (!$) return;
    $.extend(Number.prototype, {
        comma: function (length) {
            if (!length || length < 1) length = 3;
            source = ('' + this).split(".");
            source[0] = source[0].replace(new RegExp('(\\d)(?=(\\d{' + length + '})+$)', 'ig'), "$1,");
            return source.join(".");
        },
        randomInt: function (min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        },
        padLeft: function (width, ch) {
            return ('' + this).padLeft(width, ch);
        },
        padRight: function (width, ch) {
            return ('' + this).padRight(width, ch);
        },
        fileSize: function () {
            if (this === 0) return '0 B';
            var k = 1024, // or 1024
                sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                i = Math.floor(Math.log(this) / Math.log(k));
            return (this / Math.pow(k, i)) + sizes[i];
        }
    });
})(jQuery);
/* Array 原型方法扩展 */
(function ($) {
    if (!$) return;
    $.extend(Array.prototype, {
        indexOf: function (item, it) {
            for (var i = 0; i < this.length; i++) {
                if (item == ((it) ? this[i][it] : this[i])) return i;
            }
            return -1;
        },
        remove: function (item, it) {
            this.removeAt(this.indexOf(item, it));
        },
        removeAt: function (idx) {
            if (idx >= 0 && idx < this.length) {
                for (var i = idx; i < this.length - 1; i++) {
                    this[i] = this[i + 1];
                }
                this.length--;
            }
        },
        removeEmpty: function () {
            var arr = [];
            for (var i = 0; i < this.length; i++) {
                if (this[i].trim() != '') {
                    arr.push(this[i].trim());
                }
            }
            return arr;
        },
        add: function (item) {
            if (this.indexOf(item) > -1) {
                return false;
            } else {
                this.push(item);
                return true;
            }
        },
        swap: function (i, j) {
            if (i < this.length && j < this.length && i != j) {
                var item = this[i];
                this[i] = this[j];
                this[j] = item;
            }
        },
        filtera: function (it, item) {
            var arr = [];
            for (var i = 0; i < this.length; i++) {
                if (typeof (item) == 'undefined') {
                    arr.push(this[i][it]);
                } else if (this[i][it] == item) {
                    arr.push(this[i]);
                }
            }
            return arr;
        },
        unique: function () {
            var a = [],
                o = {},
                i,
                v,
                len = this.length;
            if (len < 2) return this;
            for (i = 0; i < len; i++) {
                v = this[i];
                if (o[v] !== 1) {
                    a.push(v);
                    o[v] = 1;
                }
            }
            return a;
        },
        sortby: function (it, dt, od) {
            var compareValues = function (v1, v2, dt, od) {
                if (dt == 'int') {
                    v1 = parseInt(v1);
                    v2 = parseInt(v2);
                } else if (dt == 'float') {
                    v1 = parseFloat(v1);
                    v2 = parseFloat(v2);
                }
                var ret = 0;
                if (v1 < v2) ret = 1;
                if (v1 > v2) ret = -1;
                if (od == 'desc') {
                    ret = 0 - ret;
                }
                return ret;
            };
            var newdata = [];
            for (var i = 0; i < this.length; i++) {
                newdata[newdata.length] = this[i];
            }
            for (var i = 0; i < newdata.length; i++) {
                var minIdx = i;
                var minData = (it != '') ? newdata[i][it] : newdata[i];
                for (var j = i + 1; j < newdata.length; j++) {
                    var tmpData = (it != '') ? newdata[j][it] : newdata[j];
                    var cmp = compareValues(minData, tmpData, dt, od);
                    if (cmp < 0) {
                        minIdx = j;
                        minData = tmpData;
                    }
                }
                if (minIdx > i) {
                    var _child = newdata[minIdx];
                    newdata[minIdx] = newdata[i];
                    newdata[i] = _child;
                }
            }
            return newdata;
        },

        toSting: function (it) {
            if (this.length <= 0) return "";
            if (!it) var it = ",";
            var temp = "";
            $(this).each(function (i, v) {
                temp += v + it;
            });
            return temp.trimEnd(it);
        }
    });
})(jQuery);
/* Date 原型方法扩展 */
(function ($) {
    if (!$) return;
    $.extend(Date.prototype, {
        parse: function (time) {
            if (typeof (time) == 'string') {
                if (time.indexOf('GMT') > 0 || time.indexOf('gmt') > 0 || !isNaN(Date.parse(time))) {
                    return this._parseGMT(time);
                } else if (time.indexOf('UTC') > 0 || time.indexOf('utc') > 0 || time.indexOf(',') > 0) {
                    return this._parseUTC(time);
                } else {
                    return this._parseCommon(time);
                }
            }
            return new Date();
        },
        _parseGMT: function (time) {
            this.setTime(Date.parse(time));
            return this;
        },
        _parseUTC: function (time) {
            return (new Date(time));
        },
        _parseCommon: function (time) {
            var d = time.split(/ |T/),
                d1 = d.length > 1 ? d[1].split(/[^\d]/) : [0, 0, 0],
                d0 = d[0].split(/[^\d]/);
            return new Date(d0[0] - 0, d0[1] - 1, d0[2] - 0, d1[0] - 0, d1[1] - 0, d1[2] - 0);
        },
        dateAdd: function (type, val) {
            var _y = this.getFullYear();
            var _m = this.getMonth();
            var _d = this.getDate();
            var _h = this.getHours();
            var _n = this.getMinutes();
            var _s = this.getSeconds();
            switch (type) {
                case 'y':
                    this.setFullYear(_y + val);
                    break;
                case 'm':
                    this.setMonth(_m + val);
                    break;
                case 'd':
                    this.setDate(_d + val);
                    break;
                case 'h':
                    this.setHours(_h + val);
                    break;
                case 'n':
                    this.setMinutes(_n + val);
                    break;
                case 's':
                    this.setSeconds(_s + val);
                    break;
            }
            return this;
        },
        dateDiff: function (type, date2) {
            var diff = date2 - this;
            switch (type) {
                case 'w':
                    return diff / 1000 / 3600 / 24 / 7;
                case 'd':
                    return diff / 1000 / 3600 / 24;
                case 'h':
                    return diff / 1000 / 3600;
                case 'n':
                    return diff / 1000 / 60;
                case 's':
                    return diff / 1000;
            }
        },
        format: function (formatStr) {
            var str = formatStr;
            var Week = ['日', '一', '二', '三', '四', '五', '六'];
            str = str.replace(/yyyy|YYYY/, this.getFullYear());
            str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
            str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
            str = str.replace(/M/g, this.getMonth() + 1);

            str = str.replace(/w|W/g, Week[this.getDay()]);

            str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
            str = str.replace(/d|D/g, this.getDate());

            str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
            str = str.replace(/h|H/g, this.getHours());
            str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
            str = str.replace(/m/g, this.getMinutes());

            str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
            str = str.replace(/s|S/g, this.getSeconds());

            return str;
        }
    });
})(jQuery);
/* Spp.cookie */
(function ($) {
    if (!$ || !window.Spp) return;
    Spp.cookie = {
        getRootDomain: function () {
            var d = document.domain;
            if (d.indexOf('.') > 0 && !d.isIP()) {
                var arr = d.split('.'),
                    len = arr.length,
                    d1 = arr[len - 1],
                    d2 = arr[len - 2],
                    d3 = arr[len - 3];
                d = (d2 == 'com' || d2 == 'net') ? (d3 + '.' + d2 + '.' + d1) : (d2 + '.' + d1);
            }
            return d;
        },
        load: function (tKey) {
            var tC = document.cookie.split('; ');
            var tO = {};
            var a = null;
            for (var i = 0; i < tC.length; i++) {
                a = tC[i].split('=');
                tO[a[0]] = a[1];
            }
            return tO;
        },
        get: function (name) {
            var value = this.load()[name];
            if (value) {
                try {
                    return decodeURI(value);
                } catch (e) {
                    return unescape(value);
                }
            } else {
                return false;
            }
        },
        set: function (name, value, options) {
            var options = (typeof (options) == 'object') ? options : {
                minute: options
            };
            var arg_len = arguments.length;
            var path = (arg_len > 3) ? arguments[3] : (options.path || '/');
            var domain = (arg_len > 4) ? arguments[4] : (options.domain || (options.root ? this.getRootDomain() : ''));
            var exptime = 0;
            if (options.day) {
                exptime = 1000 * 60 * 60 * 24 * options.day;
            } else if (options.hour) {
                exptime = 1000 * 60 * 60 * options.hour;
            } else if (options.minute) {
                exptime = 1000 * 60 * options.minute;
            } else if (options.second) {
                exptime = 1000 * options.second;
            }
            var exp = new Date(),
                expires = '';
            if (exptime > 0) {
                exp.setTime(exp.getTime() + exptime);
                expires = '; expires=' + exp.toGMTString();
            }
            domain = (domain) ? ('; domain=' + domain) : '';
            document.cookie = name + '=' + escape(value || '') + '; path=' + path + domain + expires;
        },
        del: function (name, options) {
            var options = options || '';
            var path = '; path=' + (options.path || '/');
            var domain = (options.domain) ? ('; domain=' + options.domain) : '';
            if (options.root) domain = '; domain=' + this.getRootDomain();
            document.cookie = name + '=' + path + domain + '; expires=Thu,01-Jan-70 00:00:01 GMT';
        }
    };
})(jQuery);
/* Spp.json */
(function ($) {
    if (!$ || !window.Spp) return;
    Spp.json = {
        parse: function (data) {
            var rd = {};

            switch ($.type(data)) {
                case "array":
                    $.each(data, function (i, v) {
                        rd[v["name"]] = v["value"];
                    });
                    break;
                case "object":
                    rd = data;
                    break;
                case "string":
                    rd = (new Function("return " + data))();
                    break;

            }
            return rd;
        },
        stringify: function (obj) {
            var m = {
                '\b': '\\b',
                '\t': '\\t',
                '\n': '\\n',
                '\f': '\\f',
                '\r': '\\r',
                '"': '\\"',
                '\\': '\\\\'
            };
            var s = {
                'array': function (x) {
                    var a = ['['],
                        b,
                        f,
                        i,
                        l = x.length,
                        v;
                    for (i = 0; i < l; i += 1) {
                        v = x[i];
                        f = s[typeof v];
                        if (f) {
                            v = f(v);
                            if (typeof v == 'string') {
                                if (b) {
                                    a[a.length] = ','
                                }
                                a[a.length] = v;
                                b = true
                            }
                        }
                    }
                    a[a.length] = ']';
                    return a.join('')
                },
                'boolean': function (x) {
                    return String(x)
                },
                'null': function (x) {
                    return 'null'
                },
                'number': function (x) {
                    return isFinite(x) ? String(x) : 'null'
                },
                'object': function (x) {
                    if (x) {
                        if (x instanceof Array) {
                            return s.array(x)
                        }
                        var a = ['{'],
                            b,
                            f,
                            i,
                            v;
                        for (i in x) {
                            v = x[i];
                            f = s[typeof v];
                            if (f) {
                                v = f(v);
                                if (typeof v == 'string') {
                                    if (b) {
                                        a[a.length] = ','
                                    }
                                    a.push(s.string(i), ':', v);
                                    b = true
                                }
                            }
                        }
                        a[a.length] = '}';
                        return a.join('')
                    }
                    return 'null'
                },
                'string': function (x) {
                    if (/["\\\x00-\x1f]/.test(x)) {
                        x = x.replace(/([\x00-\x1f\\"])/g,
                            function (a, b) {
                                var c = m[b];
                                if (c) {
                                    return c
                                }
                                c = b.charCodeAt();
                                return '\\u00' + Math.floor(c / 16).toString(16) + (c % 16).toString(16)
                            })
                    }
                    return '\"' + x + '\"'
                }
            };
            return s.object(obj);
        },
        discarts: function (data) {
            //笛卡尔积
            var twodDscartes = function (a, b) {
                var ret = [];
                for (var i = 0; i < a.length; i++) {
                    for (var j = 0; j < b.length; j++) {
                        ret.push(ft(a[i], b[j]));
                    }
                }
                return ret;
            }
            var ft = function (a, b) {
                if (!(a instanceof Array))
                    a = [a];
                var ret = a.slice(0);
                ret.push(b);
                return ret;
            }
            var len = data.length;
            if (len == 0)
                return [];
            else if (len == 1) {
                var arr = [];
                $.each(data[0].item, function (i, v) {
                    arr[i] = [];
                    arr[i].push(v);
                })
                return arr;
            }
            else {
                var r = data[0].item;
                for (var i = 1; i < len; i++) {
                    r = twodDscartes(r, data[i].item);
                }
                return r;
            }
        },
    };
})(jQuery);
/*Spp.dialog*/
(function ($) {
    Spp.dialog = {
        utilText: {
            'close': '关 闭',
            'loading': '正在加载中……',
            'loadingError': '加载失败',
            'confirm': '确 认',
            'cancel': '取 消',
            'alertTitle': '消息提示',
            'confirmTitle': '确认提示'
        },
        setting: {
            "closeshow": false,
            "callback": null,
            "closeFunction": null
        },
        // 弹窗模板
        tmpls: {
            'frameset': [
                '<div class="modal" id="spp_dialog" role="dialog">',
                '<div class="modal-dialog">',
                '<div class="modal-content">',
                '<div class="modal-header"><button type="button" class="close" onclick="Spp.dialog.close()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button><h4 class="modal-title"></h4></div>',
                '<div class="modal-body"></div>',
                '<div class="modal-footer"></div>',
                '</div>',
                '</div>',
                '</div>',
            ].join(''),
            'bottom': '<button type="button" class="btn btn-default margin-right cancel" onclick="Spp.dialog.close()">{cancel}</button><button type="button" class="btn btn-primary action">{confirm}</button>',
            'iframe': '<iframe id="{iframeId}" name="{iframeId}" src="about:blank" width="100%" height="{height}px" scrolling="auto" frameborder="0" marginheight="0" marginwidth="0"></iframe>',
            'loading': '<p class="txt-loading-32" >{loadingText}</p>',
            'alert': '<div class="msg-{type}">{message}</div>',
            'confirm': '<div class="msg-confirm">{message}</div>',
            "msg": '<div class="message"><div class="content {type}icon">{message}</div></div> ',
            "progress": ' <div class="progress margin-none" ><div class="progress-bar" style="width:0%">0%</div></div><p class="text-center ">{message}</p>'
        },
        // 初始化弹出窗
        init: function () {
            this.dialog = $("#spp_dialog");
            if (!this.dialog[0]) {
                this.dialog = $(this.tmpls.frameset.substitute(this.utilText));
                $('body').append(this.dialog);
                $this = this;
            }
        },
        // 打开弹出窗
        open: function (options) {
            if (this.lastContentId) {
                $('body').append($('#' + this.lastContentId).hide());
                this.lastContentId = null;
            }
            var $this = this;
            this.init();
            this.options = $.extend({}, this.setting, options);
            if (this.options.title) {
                this.dialog.find('.modal-title').html(this.options.title);
                this.dialog.find('.modal-header').show();
                if (!this.options.closeshow)
                    this.dialog.find("button.close").hide();
            } else {
                this.dialog.find('.modal-header').hide();
            }
            if (this.options.bottom) {
                this.setBottom(this.options.bottom);
                this.dialog.find('.modal-footer').show();
            } else {
                this.dialog.find('.modal-footer').hide();
            }
            var _content = this.dialog.find('.modal-body');
            switch (options.mode || 'text') {
                case 'text':
                    _content.html(this.options.content);
                    break;
                case 'id':
                    _content.html("");
                    var div = $('#' + this.options.id);
                    _content.append(div.show());
                    this.lastContentId = options.id;
                    break;
                case 'iframe':
                    this.iframeId = 'iframe_' + (new Date()).getTime();
                    _content.html(this.tmpls.iframe.substitute({iframeId: this.iframeId, height: this.options.height}));
                    $('#' + this.iframeId).attr('src', this.options.url);
                    break;
            }
            this.dialog.modal({backdrop: 'static'});
            this.dialog.find(".modal-footer .btn:last").focus();
            this.resize();
        },
        // 调整弹出窗大小
        resize: function () {
            var $dialog = this.dialog.find(".modal-dialog");
            width = Math.min($(window).width() * 80 / 100, (this.options.width || 320));
            $dialog.css('width', width + 30 + 'px');
            if (this.options.height)
                $dialog.css('height', height + 20 + 'px');
            /*  var _top = (this.options.top) ? this.options.top : ($(window).height() - ($dialog.height() + 80)) / 2;
             $dialog.css({
             top: $(document).scrollTop() + Math.max(_top, 0) + 'px'
             });*/
        },
        // 关闭弹出窗
        close: function () {
            var $this = this;
            if (this.options.id)
                $('body').append($('#' + this.options.id).hide());
            if (this.options.closeFunction) {
                setTimeout(function () {
                    $this.options.closeFunction();
                    $this.options.closeFunction = null;
                }, 0);
            }
            this.dialog.modal("hide");
        },
        // 更换弹出窗底部内容 { html, noteHtml, actionHtml, actionBtnText, cancelBtnText, callback }
        setBottom: function (options) {
            if (!options) return;
            if (options.html) {
                this.dialog.find('.modal-footer').html(options.html);
            } else {
                this.dialog.find('.modal-footer').html(this.tmpls.bottom.substitute(this.utilText));
                if (options.action) this.dialog.find('.modal-footer .action').text(options.action);
                if (options.cancel) this.dialog.find('.modal-footer .cancel').text(options.cancel);
            }
            if (options.callback) {
                this.dialog.find('.modal-footer .action').click(function () {
                    options.callback();
                });
            }
        },
        // 打开新页面
        url: function (url) {
            height = parseInt(url.getQueryValue("h")) || 150, width = parseInt(url.getQueryValue("w")) || 400, title = url.getQueryValue("title"),
                url = url.removeQueryValue("w").removeQueryValue("h").removeQueryValue("title").addQueryValue("title", title.encodeURI());
            var params = {
                url: url,
                title: title || "操作窗口",
                height: height,
                width: width,
                mode: 'iframe',
                closeshow: true
            };
            this.open(params);
            return false;
        },
        // 显示弹出窗内容
        div: function () {
            var params = {};
            if ($.isPlainObject(arguments[0])) {
                params = arguments[0];
            } else if (typeof arguments[0] === "string") {
                params.id = arguments[0];
                params.title = arguments[1];
            } else {
                return false;
            }
            params.closeshow = true;
            params.mode = 'id';
            params.width = params.width || $("#" + params.id).width();
            this.open(params);
            return false;
        },

        msg: function () {
            var params = {};
            if ($.isPlainObject(arguments[0])) {
                params = arguments[0];
            } else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
                params.type = arguments[0];
                params.message = arguments[1];
                params.closeFunction = arguments[2];
            } else {
                return false;
            }
            params.width = params.message.length * 16 + 50;
            var content = this.tmpls.msg.substitute(params);
            params.mode = 'text';
            params.content = content;
            this.open(params);
            setTimeout(function () {
                Spp.dialog.close();
            }, 1000);
            return false;
        },
        progress: function () {
            value = arguments[0];
            if (!isNaN(value)) {
                var progress = this.dialog.find(".progress-bar");
                progress.css("width", Math.round(value) + "%").html(Math.round(value) + "%");
                return false;
            }
            var params = {};
            params.message = value;
            var content = this.tmpls.progress.substitute(params);
            params.width = 300;
            params.mode = 'text';
            params.content = content;
            this.open(params);
            return false;
        },
        // 显示加载进度
        loading: function () {
            var params = {};
            if ($.isPlainObject(arguments[0])) {
                params = arguments[0];
            } else {
                params.loadingText = arguments[0];
            }
            params.mode = 'text';
            params.content = this.tmpls.loading.substitute(params);
            if (params.loadingText)
                params.width = params.loadingText.length * 16 + 50;
            else
                params.width = 15;
            this.open(params);
            return false;
        },
        // 打开提醒框
        alert: function () {
            var params = {};
            if ($.isPlainObject(arguments[0])) {
                params = arguments[0];
            } else {
                params.message = arguments[0];
                params.closeFunction = arguments[1]
            }
            params.close = params.close || this.utilText.close;
            params.type = params.type || 'alert';
            params.bottom = {"html": '<button type="button" class="btn btn-default" onclick="Spp.dialog.close()">' + params.close + '</button>'};
            var content = this.tmpls.alert.substitute(params);
            params.mode = 'text';
            params.title = params.title || this.utilText.alertTitle;
            params.content = content;
            this.open(params);
            return false;
        },
        // 打开确认框
        confirm: function () {
            var params = {};
            if ($.isPlainObject(arguments[0])) {
                params = arguments[0];
            } else {
                params.message = arguments[0];
                params.bottom = {callback: arguments[1]};
                params.closeFunction = arguments[2];
            }
            var content = this.tmpls.confirm.substitute(params);
            params.mode = 'text';
            params.title = params.title || this.utilText.confirmTitle;
            params.content = content;
            this.open(params);
            return false;
        }

    }
})(jQuery);
/* Spp.page */
(function ($) {
    Spp.page = {
        param: {
            scriptsArray: new Array(),
            cssArray: new Array()
        },
        loadJs: function (url, root, method) {
            if (typeof root == 'function' || !root) {
                method = root;
                url = res + "/js/" + url;
            } else if (typeof root == "string")
                url = root + url;
            if (this.param.scriptsArray.indexOf(url) >= 0 && typeof method == 'function')
                method();
            else {
                options = {
                    dataType: "script",
                    url: url,
                    success: function () {
                        if (typeof method == 'function')
                            method();
                    }
                };
                this.param.scriptsArray.add(url);
                $.ajax(options)
            }
        },
        loadCss: function (url, root) {
            url = (root ? root : res) + "/" + url;
            if (this.param.cssArray.indexOf(url) == -1) {
                var head = document.getElementsByTagName('head')[0];
                var link = document.createElement('link');
                link.href = url;
                link.rel = 'stylesheet';
                link.type = 'text/css';
                head.appendChild(link);
                this.param.cssArray.add(url);
            }
        },
        bindData: function (obj, data) {
            if (!data) return;
            var el = $(":text,[type='password'],[type='hidden'], [type='file'], select, textarea, " +
                "[type='number'], [type='search'] ,[type='tel'], [type='url'], " +
                "[type='email'], [type='datetime'], [type='date'], [type='month'], " +
                "[type='week'], [type='time'], [type='datetime-local'], " +
                "[type='range'], [type='color'],[type='radio'], [type='checkbox']", obj);
            el.each(function (i, v) {
                vname = v.type == "checkbox" ? v.name.trimEnd("]").trimEnd("[") : v.name;
                if (data[vname] && data[vname] != '') {
                    if ((/radio|checkbox/i).test(v.type))
                        v.checked = data[vname].split(',').indexOf(v.value) != -1;
                    else if (v.type == "select-one") {
                        v.value = data[vname];
                        $(v).trigger("change");
                    }
                    else
                        v.value = data[vname]
                }
            });
        },
        bindUrl: function (url, div) {
            var el = $(":text,[type='password'],[type='hidden'], [type='file'], select, textarea, " +
                "[type='number'], [type='search'] ,[type='tel'], [type='url'], " +
                "[type='email'], [type='datetime'], [type='date'], [type='month'], " +
                "[type='week'], [type='time'], [type='datetime-local'], " +
                "[type='range'], [type='color'],[type='radio'], [type='checkbox']", div);
            el.each(function (i, v) {
                if (v.value.trimAll() == '')
                    url = url.removeQueryValue(v.name);
                else {
                    if ((/radio|checkbox/i).test(v.type)) {
                        if (v.checked)
                            url = url.addQueryValue(v.name, (url.getQueryValue(v.name) ? url.getQueryValue(v.name) + "," : "") + v.value)
                    }
                    else
                        url = url.addQueryValue(v.name, v.value);
                }
            });
            return url;
        }
    }
})(jQuery);


