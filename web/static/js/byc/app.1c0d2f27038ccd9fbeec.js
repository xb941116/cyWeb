webpackJsonp([48],{

/***/ 100:
/***/ (function(module, exports, __webpack_require__) {

function injectStyle (ssrContext) {
  __webpack_require__(240)
}
var Component = __webpack_require__(24)(
  /* script */
  __webpack_require__(175),
  /* template */
  __webpack_require__(251),
  /* styles */
  injectStyle,
  /* scopeId */
  null,
  /* moduleIdentifier (server only) */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 101:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__ = __webpack_require__(105);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__assets_js_utils___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__);
/* harmony reexport (binding) */ if(__webpack_require__.o(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__, "formatTime")) __webpack_require__.d(__webpack_exports__, "formatTime", function() { return __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__["formatTime"]; });
/* harmony reexport (binding) */ if(__webpack_require__.o(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__, "formatDay")) __webpack_require__.d(__webpack_exports__, "formatDay", function() { return __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__["formatDay"]; });
/* harmony reexport (binding) */ if(__webpack_require__.o(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__, "formatDayWithUnit")) __webpack_require__.d(__webpack_exports__, "formatDayWithUnit", function() { return __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__["formatDayWithUnit"]; });
/* harmony reexport (binding) */ if(__webpack_require__.o(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__, "timeAgo")) __webpack_require__.d(__webpack_exports__, "timeAgo", function() { return __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__["timeAgo"]; });
/* harmony reexport (binding) */ if(__webpack_require__.o(__WEBPACK_IMPORTED_MODULE_0__assets_js_utils__, "formatToFixed")) __webpack_require__.d(__webpack_exports__, "formatToFixed", function() { return __WEBPACK_IMPORTED_MODULE_0__assets_js_utils__["formatToFixed"]; });
// 从工具库utils.js导入


// other filter
// ...


// 导出对象


/***/ }),

/***/ 102:
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(global) {/**
 * Vue-html5-editor 1.1.1
 * https://github.com/PeakTai/vue-html5-editor
 * build at Thu Apr 20 2017 16:31:37 GMT+0800 (CST)
 */

(function (global, factory) {
	 true ? module.exports = factory() :
	typeof define === 'function' && define.amd ? define(factory) :
	(global.VueHtml5Editor = factory());
}(this, (function () { 'use strict';

function __$styleInject(css, returnValue) {
  if (typeof document === 'undefined') {
    return returnValue;
  }
  css = css || '';
  var head = document.head || document.getElementsByTagName('head')[0];
  var style = document.createElement('style');
  style.type = 'text/css';
  if (style.styleSheet){
    style.styleSheet.cssText = css;
  } else {
    style.appendChild(document.createTextNode(css));
  }
  head.appendChild(style);
  return returnValue;
}

var polyfill = function () {
    // https://tc39.github.io/ecma262/#sec-array.prototype.includes
    if (!Array.prototype.includes) {
        Object.defineProperty(Array.prototype, 'includes', {
            value: function value(searchElement, fromIndex) {
                // 1. Let O be ? ToObject(this value).
                if (this == null) {
                    throw new TypeError('"this" is null or not defined')
                }

                var o = Object(this);

                // 2. Let len be ? ToLength(? Get(O, "length")).
                var len = o.length >>> 0;

                // 3. If len is 0, return false.
                if (len === 0) {
                    return false
                }

                // 4. Let n be ? ToInteger(fromIndex).
                //    (If fromIndex is undefined, this step produces the value 0.)
                var n = fromIndex | 0;

                // 5. If n ≥ 0, then
                //  a. Let k be n.
                // 6. Else n < 0,
                //  a. Let k be len + n.
                //  b. If k < 0, let k be 0.
                var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

                // 7. Repeat, while k < len
                while (k < len) {
                    // a. Let elementK be the result of ? Get(O, ! ToString(k)).
                    // b. If SameValueZero(searchElement, elementK) is true, return true.
                    // c. Increase k by 1.
                    // NOTE: === provides the correct "SameValueZero" comparison needed here.
                    if (o[k] === searchElement) {
                        return true
                    }
                    k++;
                }

                // 8. Return false
                return false
            }
        });
    }
    // text.contains()
    if (!Text.prototype.contains) {
        Text.prototype.contains = function contains(node) {
            return this === node
        };
    }
};

var template = "<div> <button type=\"button\" @click=\"$parent.execCommand('justifyLeft')\"> {{$parent.locale[\"left justify\"]}} </button> <button type=\"button\" @click=\"$parent.execCommand('justifyCenter')\"> {{$parent.locale[\"center justify\"]}} </button> <button type=\"button\" @click=\"$parent.execCommand('justifyRight')\"> {{$parent.locale[\"right justify\"]}} </button> </div>";

/**
 * Created by peak on 2017/2/10.
 */
var dashboard = {
    template: template
};

/**
 * text align
 * Created by peak on 16/8/18.
 */
var align = {
    name: 'align',
    icon: 'fa fa-align-center',
    i18n: 'align',
    dashboard: dashboard
};

var template$1 = "<div> <div> <label> <input type=\"radio\" value=\"foreColor\" v-model=\"command\">&nbsp; {{$parent.locale[\"fore color\"]}} </label> <label> <input type=\"radio\" value=\"backColor\" v-model=\"command\">&nbsp; {{$parent.locale[\"background color\"]}} </label> </div> <div> <div v-for=\"color in colors\" :style=\"{'background-color':color}\" class=\"color-card\" @click=\"changeColor(color)\"> </div> <div style=\"clear: both\"></div> </div> </div> ";

__$styleInject(".vue-html5-editor .color-card{margin:2px;width:30px;height:30px;float:left;cursor:pointer}",undefined);

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$1 = {
    template: template$1,
    data: function data(){
        return {
            // foreColor,backColor
            command: 'foreColor',
            colors: [
                '#000000', '#000033', '#000066', '#000099', '#003300', '#003333', '#003366',
                '#003399', '#006600', '#006633', '#009900', '#330000', '#330033', '#330066',
                '#333300', '#333366', '#660000', '#660033', '#663300', '#666600', '#666633',
                '#666666', '#666699', '#990000', '#990033', '#9900CC', '#996600', '#FFCC00',
                '#FFCCCC', '#FFCC99', '#FFFF00', '#FF9900', '#CCFFCC', '#CCFFFF', '#CCFF99'
            ]
        }
    },
    methods: {
        changeColor: function changeColor(color){
            this.$parent.execCommand(this.command, color);
        }
    }
};

/**
 * fore color and back color
 * Created by peak on 16/8/18.
 */
var color = {
    name: 'color',
    icon: 'fa fa-paint-brush',
    i18n: 'color',
    dashboard: dashboard$1
};

/**
 * remove format of selection
 * Created by peak on 16/8/18.
 */
var eraser = {
    name: 'eraser',
    icon: 'fa fa-eraser',
    i18n: 'eraser',
    handler: function handler(editor) {
        editor.execCommand('removeFormat');
    }
};

var template$2 = "<div class=\"dashboard-font\" style=\"line-height: 36px\"> <div> <label>{{$parent.locale[\"heading\"]}}:</label> <button v-for=\"h in 6\" type=\"button\" @click=\"setHeading(h)\">H{{h}}</button> </div> <div> <label> {{$parent.locale[\"font name\"]}}: </label> <button v-for=\"name in nameList\" type=\"button\" @click=\"setFontName(name)\">{{name}}</button> </div> <div> <label> {{$parent.locale[\"font size\"]}}: </label> <button v-for=\"size in fontSizeList\" type=\"button\" @click=\"setFontSize(size)\">{{size}}</button> </div> <div> <label> {{$parent.locale[\"line height\"]}}: </label> <button v-for=\"lh in lineHeightList\" type=\"button\" @click=\"setLineHeight(lh)\"> {{lh}} </button> </div> </div>";

/**
 * Created by peak on 2017/2/14.
 */
var Command = {
    JUSTIFY_LEFT: 'justifyLeft',
    JUSTIFY_CENTER: 'justifyCenter',
    JUSTIFY_RIGHT: 'justifyRight',
    FORE_COLOR: 'foreColor',
    BACK_COLOR: 'backColor',
    REMOVE_FORMAT: 'removeFormat',
    FONT_NAME: 'fontName',
    FONT_SIZE: 'fontSize',
    FORMAT_BLOCK: 'formatBlock',
    LINE_HEIGHT: 'lineHeight',
    INSERT_HORIZONTAL_RULE: 'insertHorizontalRule',
    INSERT_IMAGE: 'insertImage',
    CREATE_LINK: 'createLink',
    INSERT_ORDERED_LIST: 'insertOrderedList',
    INSERT_UNORDERED_LIST: 'insertUnorderedList',
    INSERT_HTML: 'insertHTML',
    BOLD: 'bold',
    ITALIC: 'italic',
    UNDERLINE: 'underline',
    STRIKE_THROUGH: 'strikeThrough',
    SUBSCRIPT: 'subscript',
    SUPERSCRIPT: 'superscript',
    UNDO: 'undo',
    UNLINK: 'unlink'
};

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$2 = {
    template: template$2,
    data: function data(){
        return {
            nameList: [
                'Microsoft YaHei',
                'Helvetica Neue',
                'Helvetica',
                'Arial',
                'sans-serif',
                'Verdana',
                'Georgia',
                'Times New Roman',
                'Trebuchet MS',
                'Microsoft JhengHei',
                'Courier New',
                'Impact',
                'Comic Sans MS',
                'Consolas'
            ],
            lineHeightList: [
                '1.0', '1.2', '1.5', '1.8', '2.0', '2.5', '3.0'
            ],
            fontSizeList: [
                '12px', '14px', '16px', '18px', '20px', '22px', '24px'
            ]
        }
    },
    methods: {
        setFontName: function setFontName(name){
            this.$parent.execCommand('fontName', name);
        },
        setFontSize: function setFontSize(size){
            this.$parent.execCommand('fontSize', size);
        },
        setHeading: function setHeading(heading){
            this.$parent.execCommand('formatBlock', ("h" + heading));
        },
        setLineHeight: function setLineHeight(lh){
            this.$parent.execCommand(Command.LINE_HEIGHT, lh);
        }
    },
    created: function created(){
        var config = this.$options.module.config;
        // font name
        if (!config) {
            return
        }
        if (Array.isArray(config.fontNames)) {
            this.nameList = config.fontNames;
        }
    }
};

/**
 * font name and font size
 * Created by peak on 16/8/18.
 */
var font = {
    name: 'font',
    icon: 'fa fa-font',
    i18n: 'font',
    dashboard: dashboard$2
};

/**
 * toggle full screen mode
 * Created by peak on 16/8/18.
 */
var fullScreen$1 = {
    name: 'full-screen',
    icon: 'fa fa-arrows-alt',
    i18n: 'full screen',
    handler: function handler(editor) {
        editor.toggleFullScreen();
    }
};

/**
 * hr
 * Created by peak on 16/8/20.
 */
var hr = {
    name: 'hr',
    icon: 'fa fa-minus',
    i18n: 'hr',
    handler: function handler(editor) {
        editor.execCommand('insertHorizontalRule');
    }
    // init (editor) {
    //
    // },
    // destroyed(editor){
    //
    // },
};

var commonjsGlobal = typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};





function createCommonjsModule(fn, module) {
	return module = { exports: {} }, fn(module, module.exports), module.exports;
}

var lrz_all_bundle = createCommonjsModule(function (module, exports) {
!function(e,t){if("object"==typeof exports&&"object"==typeof module){ module.exports=t(); }else if(false){ undefined([],t); }else{var n=t();for(var r in n){ ("object"==typeof exports?exports:e)[r]=n[r]; }}}(commonjsGlobal,function(){return function(e){function t(r){if(n[r]){ return n[r].exports; }var i=n[r]={exports:{},id:r,loaded:!1};return e[r].call(i.exports,i,i.exports,t),i.loaded=!0,i.exports}var n={};return t.m=e,t.c=n,t.p="",t(0)}([function(e,t,n){n(6),n(7),e.exports=n(8);},function(e,t,n){(function(t){!function(n){function r(e,t){return function(){e.apply(t,arguments);}}function i(e){if("object"!=typeof this){ throw new TypeError("Promises must be constructed via new"); }if("function"!=typeof e){ throw new TypeError("not a function"); }this._state=null,this._value=null,this._deferreds=[],l(e,r(a,this),r(s,this));}function o(e){var t=this;return null===this._state?void this._deferreds.push(e):void f(function(){var n=t._state?e.onFulfilled:e.onRejected;if(null===n){ return void(t._state?e.resolve:e.reject)(t._value); }var r;try{r=n(t._value);}catch(i){return void e.reject(i)}e.resolve(r);})}function a(e){try{if(e===this){ throw new TypeError("A promise cannot be resolved with itself."); }if(e&&("object"==typeof e||"function"==typeof e)){var t=e.then;if("function"==typeof t){ return void l(r(t,e),r(a,this),r(s,this)) }}this._state=!0,this._value=e,u.call(this);}catch(n){s.call(this,n);}}function s(e){this._state=!1,this._value=e,u.call(this);}function u(){
var this$1 = this;
for(var e=0,t=this._deferreds.length;t>e;e++){ o.call(this$1,this$1._deferreds[e]); }this._deferreds=null;}function c(e,t,n,r){this.onFulfilled="function"==typeof e?e:null,this.onRejected="function"==typeof t?t:null,this.resolve=n,this.reject=r;}function l(e,t,n){var r=!1;try{e(function(e){r||(r=!0,t(e));},function(e){r||(r=!0,n(e));});}catch(i){if(r){ return; }r=!0,n(i);}}var f="function"==typeof t&&t||function(e){setTimeout(e,1);},d=Array.isArray||function(e){return"[object Array]"===Object.prototype.toString.call(e)};i.prototype["catch"]=function(e){return this.then(null,e)},i.prototype.then=function(e,t){var n=this;return new i(function(r,i){o.call(n,new c(e,t,r,i));})},i.all=function(){var e=Array.prototype.slice.call(1===arguments.length&&d(arguments[0])?arguments[0]:arguments);return new i(function(t,n){function r(o,a){try{if(a&&("object"==typeof a||"function"==typeof a)){var s=a.then;if("function"==typeof s){ return void s.call(a,function(e){r(o,e);},n) }}e[o]=a,0===--i&&t(e);}catch(u){n(u);}}if(0===e.length){ return t([]); }for(var i=e.length,o=0;o<e.length;o++){ r(o,e[o]); }})},i.resolve=function(e){return e&&"object"==typeof e&&e.constructor===i?e:new i(function(t){t(e);})},i.reject=function(e){return new i(function(t,n){n(e);})},i.race=function(e){return new i(function(t,n){for(var r=0,i=e.length;i>r;r++){ e[r].then(t,n); }})},i._setImmediateFn=function(e){f=e;},i.prototype.always=function(e){var t=this.constructor;return this.then(function(n){return t.resolve(e()).then(function(){return n})},function(n){return t.resolve(e()).then(function(){throw n})})},"undefined"!=typeof e&&e.exports?e.exports=i:n.Promise||(n.Promise=i);}(this);}).call(t,n(2).setImmediate);},function(e,t,n){(function(e,r){function i(e,t){this._id=e,this._clearFn=t;}var o=n(3).nextTick,a=Function.prototype.apply,s=Array.prototype.slice,u={},c=0;t.setTimeout=function(){return new i(a.call(setTimeout,window,arguments),clearTimeout)},t.setInterval=function(){return new i(a.call(setInterval,window,arguments),clearInterval)},t.clearTimeout=t.clearInterval=function(e){e.close();},i.prototype.unref=i.prototype.ref=function(){},i.prototype.close=function(){this._clearFn.call(window,this._id);},t.enroll=function(e,t){clearTimeout(e._idleTimeoutId),e._idleTimeout=t;},t.unenroll=function(e){clearTimeout(e._idleTimeoutId),e._idleTimeout=-1;},t._unrefActive=t.active=function(e){clearTimeout(e._idleTimeoutId);var t=e._idleTimeout;t>=0&&(e._idleTimeoutId=setTimeout(function(){e._onTimeout&&e._onTimeout();},t));},t.setImmediate="function"==typeof e?e:function(e){var n=c++,r=arguments.length<2?!1:s.call(arguments,1);return u[n]=!0,o(function(){u[n]&&(r?e.apply(null,r):e.call(null),t.clearImmediate(n));}),n},t.clearImmediate="function"==typeof r?r:function(e){delete u[e];};}).call(t,n(2).setImmediate,n(2).clearImmediate);},function(e,t){function n(){c=!1,a.length?u=a.concat(u):l=-1,u.length&&r();}function r(){if(!c){var e=setTimeout(n);c=!0;for(var t=u.length;t;){for(a=u,u=[];++l<t;){ a&&a[l].run(); }l=-1,t=u.length;}a=null,c=!1,clearTimeout(e);}}function i(e,t){this.fun=e,this.array=t;}function o(){}var a,s=e.exports={},u=[],c=!1,l=-1;s.nextTick=function(e){
var arguments$1 = arguments;
var t=new Array(arguments.length-1);if(arguments.length>1){ for(var n=1;n<arguments.length;n++){ t[n-1]=arguments$1[n]; } }u.push(new i(e,t)),1!==u.length||c||setTimeout(r,0);},i.prototype.run=function(){this.fun.apply(null,this.array);},s.title="browser",s.browser=!0,s.env={},s.argv=[],s.version="",s.versions={},s.on=o,s.addListener=o,s.once=o,s.off=o,s.removeListener=o,s.removeAllListeners=o,s.emit=o,s.binding=function(e){throw new Error("process.binding is not supported")},s.cwd=function(){return"/"},s.chdir=function(e){throw new Error("process.chdir is not supported")},s.umask=function(){return 0};},function(e,t){function n(){var e=~navigator.userAgent.indexOf("Android")&&~navigator.vendor.indexOf("Google")&&!~navigator.userAgent.indexOf("Chrome");return e&&navigator.userAgent.match(/AppleWebKit\/(\d+)/).pop()<=534||/MQQBrowser/g.test(navigator.userAgent)}var r=function(){try{return new Blob,!0}catch(e){return!1}}()?window.Blob:function(e,t){var n=new(window.BlobBuilder||window.WebKitBlobBuilder||window.MSBlobBuilder||window.MozBlobBuilder);return e.forEach(function(e){n.append(e);}),n.getBlob(t?t.type:void 0)},i=function(){function e(){var e=this,n=[],i=Array(21).join("-")+(+new Date*(1e16*Math.random())).toString(36),o=XMLHttpRequest.prototype.send;this.getParts=function(){return n.toString()},this.append=function(e,t,r){n.push("--"+i+'\r\nContent-Disposition: form-data; name="'+e+'"'),t instanceof Blob?(n.push('; filename="'+(r||"blob")+'"\r\nContent-Type: '+t.type+"\r\n\r\n"),n.push(t)):n.push("\r\n\r\n"+t),n.push("\r\n");},t++,XMLHttpRequest.prototype.send=function(a){var s,u,c=this;a===e?(n.push("--"+i+"--\r\n"),u=new r(n),s=new FileReader,s.onload=function(){o.call(c,s.result);},s.onerror=function(e){throw e},s.readAsArrayBuffer(u),this.setRequestHeader("Content-Type","multipart/form-data; boundary="+i),t--,0==t&&(XMLHttpRequest.prototype.send=o)):o.call(this,a);};}var t=0;return e.prototype=Object.create(FormData.prototype),e}();e.exports={Blob:r,FormData:n()?i:FormData};},function(e,t,n){var r,i;(function(){function n(e){return!!e.exifdata}function o(e,t){t=t||e.match(/^data\:([^\;]+)\;base64,/im)[1]||"",e=e.replace(/^data\:([^\;]+)\;base64,/gim,"");for(var n=atob(e),r=n.length,i=new ArrayBuffer(r),o=new Uint8Array(i),a=0;r>a;a++){ o[a]=n.charCodeAt(a); }return i}function a(e,t){var n=new XMLHttpRequest;n.open("GET",e,!0),n.responseType="blob",n.onload=function(e){(200==this.status||0===this.status)&&t(this.response);},n.send();}function s(e,t){function n(n){var r=u(n),i=c(n);e.exifdata=r||{},e.iptcdata=i||{},t&&t.call(e);}if(e.src){ if(/^data\:/i.test(e.src)){var r=o(e.src);n(r);}else if(/^blob\:/i.test(e.src)){var i=new FileReader;i.onload=function(e){n(e.target.result);},a(e.src,function(e){i.readAsArrayBuffer(e);});}else{var s=new XMLHttpRequest;s.onload=function(){200==this.status||0===this.status?n(s.response):t(new Error("Could not load image")),s=null;},s.open("GET",e.src,!0),s.responseType="arraybuffer",s.send(null);} }else if(window.FileReader&&(e instanceof window.Blob||e instanceof window.File)){var i=new FileReader;i.onload=function(e){p&&console.log("Got file of length "+e.target.result.byteLength),n(e.target.result);},i.readAsArrayBuffer(e);}}function u(e){var t=new DataView(e);if(p&&console.log("Got file of length "+e.byteLength),255!=t.getUint8(0)||216!=t.getUint8(1)){ return p&&console.log("Not a valid JPEG"),!1; }for(var n,r=2,i=e.byteLength;i>r;){if(255!=t.getUint8(r)){ return p&&console.log("Not a valid marker at offset "+r+", found: "+t.getUint8(r)),!1; }if(n=t.getUint8(r+1),p&&console.log(n),225==n){ return p&&console.log("Found 0xFFE1 marker"),g(t,r+4,t.getUint16(r+2)-2); }r+=2+t.getUint16(r+2);}}function c(e){var t=new DataView(e);if(p&&console.log("Got file of length "+e.byteLength),255!=t.getUint8(0)||216!=t.getUint8(1)){ return p&&console.log("Not a valid JPEG"),!1; }for(var n=2,r=e.byteLength,i=function(e,t){return 56===e.getUint8(t)&&66===e.getUint8(t+1)&&73===e.getUint8(t+2)&&77===e.getUint8(t+3)&&4===e.getUint8(t+4)&&4===e.getUint8(t+5)};r>n;){if(i(t,n)){var o=t.getUint8(n+7);o%2!==0&&(o+=1),0===o&&(o=4);var a=n+8+o,s=t.getUint16(n+6+o);return l(e,a,s)}n++;}}function l(e,t,n){for(var r,i,o,a,s,u=new DataView(e),c={},l=t;t+n>l;){ 28===u.getUint8(l)&&2===u.getUint8(l+1)&&(a=u.getUint8(l+2),a in S&&(o=u.getInt16(l+3),s=o+5,i=S[a],r=h(u,l+5,o),c.hasOwnProperty(i)?c[i]instanceof Array?c[i].push(r):c[i]=[c[i],r]:c[i]=r)),l++; }return c}function f(e,t,n,r,i){var o,a,s,u=e.getUint16(n,!i),c={};for(s=0;u>s;s++){ o=n+12*s+2,a=r[e.getUint16(o,!i)],!a&&p&&console.log("Unknown tag: "+e.getUint16(o,!i)),c[a]=d(e,o,t,n,i); }return c}function d(e,t,n,r,i){var o,a,s,u,c,l,f=e.getUint16(t+2,!i),d=e.getUint32(t+4,!i),g=e.getUint32(t+8,!i)+n;switch(f){case 1:case 7:if(1==d){ return e.getUint8(t+8,!i); }for(o=d>4?g:t+8,a=[],u=0;d>u;u++){ a[u]=e.getUint8(o+u); }return a;case 2:return o=d>4?g:t+8,h(e,o,d-1);case 3:if(1==d){ return e.getUint16(t+8,!i); }for(o=d>2?g:t+8,a=[],u=0;d>u;u++){ a[u]=e.getUint16(o+2*u,!i); }return a;case 4:if(1==d){ return e.getUint32(t+8,!i); }for(a=[],u=0;d>u;u++){ a[u]=e.getUint32(g+4*u,!i); }return a;case 5:if(1==d){ return c=e.getUint32(g,!i),l=e.getUint32(g+4,!i),s=new Number(c/l),s.numerator=c,s.denominator=l,s; }for(a=[],u=0;d>u;u++){ c=e.getUint32(g+8*u,!i),l=e.getUint32(g+4+8*u,!i),a[u]=new Number(c/l),a[u].numerator=c,a[u].denominator=l; }return a;case 9:if(1==d){ return e.getInt32(t+8,!i); }for(a=[],u=0;d>u;u++){ a[u]=e.getInt32(g+4*u,!i); }return a;case 10:if(1==d){ return e.getInt32(g,!i)/e.getInt32(g+4,!i); }for(a=[],u=0;d>u;u++){ a[u]=e.getInt32(g+8*u,!i)/e.getInt32(g+4+8*u,!i); }return a}}function h(e,t,n){var r,i="";for(r=t;t+n>r;r++){ i+=String.fromCharCode(e.getUint8(r)); }return i}function g(e,t){if("Exif"!=h(e,t,4)){ return p&&console.log("Not valid EXIF data! "+h(e,t,4)),!1; }var n,r,i,o,a,s=t+6;if(18761==e.getUint16(s)){ n=!1; }else{if(19789!=e.getUint16(s)){ return p&&console.log("Not valid TIFF data! (no 0x4949 or 0x4D4D)"),!1; }n=!0;}if(42!=e.getUint16(s+2,!n)){ return p&&console.log("Not valid TIFF data! (no 0x002A)"),!1; }var u=e.getUint32(s+4,!n);if(8>u){ return p&&console.log("Not valid TIFF data! (First offset less than 8)",e.getUint32(s+4,!n)),!1; }if(r=f(e,s,s+u,v,n),r.ExifIFDPointer){o=f(e,s,s+r.ExifIFDPointer,w,n);for(i in o){switch(i){case"LightSource":case"Flash":case"MeteringMode":case"ExposureProgram":case"SensingMethod":case"SceneCaptureType":case"SceneType":case"CustomRendered":case"WhiteBalance":case"GainControl":case"Contrast":case"Saturation":case"Sharpness":case"SubjectDistanceRange":case"FileSource":o[i]=b[i][o[i]];break;case"ExifVersion":case"FlashpixVersion":o[i]=String.fromCharCode(o[i][0],o[i][1],o[i][2],o[i][3]);break;case"ComponentsConfiguration":o[i]=b.Components[o[i][0]]+b.Components[o[i][1]]+b.Components[o[i][2]]+b.Components[o[i][3]];}r[i]=o[i];}}if(r.GPSInfoIFDPointer){a=f(e,s,s+r.GPSInfoIFDPointer,y,n);for(i in a){switch(i){case"GPSVersionID":a[i]=a[i][0]+"."+a[i][1]+"."+a[i][2]+"."+a[i][3];}r[i]=a[i];}}return r}var p=!1,m=function(e){return e instanceof m?e:this instanceof m?void(this.EXIFwrapped=e):new m(e)};"undefined"!=typeof e&&e.exports&&(t=e.exports=m),t.EXIF=m;var w=m.Tags={36864:"ExifVersion",40960:"FlashpixVersion",40961:"ColorSpace",40962:"PixelXDimension",40963:"PixelYDimension",37121:"ComponentsConfiguration",37122:"CompressedBitsPerPixel",37500:"MakerNote",37510:"UserComment",40964:"RelatedSoundFile",36867:"DateTimeOriginal",36868:"DateTimeDigitized",37520:"SubsecTime",37521:"SubsecTimeOriginal",37522:"SubsecTimeDigitized",33434:"ExposureTime",33437:"FNumber",34850:"ExposureProgram",34852:"SpectralSensitivity",34855:"ISOSpeedRatings",34856:"OECF",37377:"ShutterSpeedValue",37378:"ApertureValue",37379:"BrightnessValue",37380:"ExposureBias",37381:"MaxApertureValue",37382:"SubjectDistance",37383:"MeteringMode",37384:"LightSource",37385:"Flash",37396:"SubjectArea",37386:"FocalLength",41483:"FlashEnergy",41484:"SpatialFrequencyResponse",41486:"FocalPlaneXResolution",41487:"FocalPlaneYResolution",41488:"FocalPlaneResolutionUnit",41492:"SubjectLocation",41493:"ExposureIndex",41495:"SensingMethod",41728:"FileSource",41729:"SceneType",41730:"CFAPattern",41985:"CustomRendered",41986:"ExposureMode",41987:"WhiteBalance",41988:"DigitalZoomRation",41989:"FocalLengthIn35mmFilm",41990:"SceneCaptureType",41991:"GainControl",41992:"Contrast",41993:"Saturation",41994:"Sharpness",41995:"DeviceSettingDescription",41996:"SubjectDistanceRange",40965:"InteroperabilityIFDPointer",42016:"ImageUniqueID"},v=m.TiffTags={256:"ImageWidth",257:"ImageHeight",34665:"ExifIFDPointer",34853:"GPSInfoIFDPointer",40965:"InteroperabilityIFDPointer",258:"BitsPerSample",259:"Compression",262:"PhotometricInterpretation",274:"Orientation",277:"SamplesPerPixel",284:"PlanarConfiguration",530:"YCbCrSubSampling",531:"YCbCrPositioning",282:"XResolution",283:"YResolution",296:"ResolutionUnit",273:"StripOffsets",278:"RowsPerStrip",279:"StripByteCounts",513:"JPEGInterchangeFormat",514:"JPEGInterchangeFormatLength",301:"TransferFunction",318:"WhitePoint",319:"PrimaryChromaticities",529:"YCbCrCoefficients",532:"ReferenceBlackWhite",306:"DateTime",270:"ImageDescription",271:"Make",272:"Model",305:"Software",315:"Artist",33432:"Copyright"},y=m.GPSTags={0:"GPSVersionID",1:"GPSLatitudeRef",2:"GPSLatitude",3:"GPSLongitudeRef",4:"GPSLongitude",5:"GPSAltitudeRef",6:"GPSAltitude",7:"GPSTimeStamp",8:"GPSSatellites",9:"GPSStatus",10:"GPSMeasureMode",11:"GPSDOP",12:"GPSSpeedRef",13:"GPSSpeed",14:"GPSTrackRef",15:"GPSTrack",16:"GPSImgDirectionRef",17:"GPSImgDirection",18:"GPSMapDatum",19:"GPSDestLatitudeRef",20:"GPSDestLatitude",21:"GPSDestLongitudeRef",22:"GPSDestLongitude",23:"GPSDestBearingRef",24:"GPSDestBearing",25:"GPSDestDistanceRef",26:"GPSDestDistance",27:"GPSProcessingMethod",28:"GPSAreaInformation",29:"GPSDateStamp",30:"GPSDifferential"},b=m.StringValues={ExposureProgram:{0:"Not defined",1:"Manual",2:"Normal program",3:"Aperture priority",4:"Shutter priority",5:"Creative program",6:"Action program",7:"Portrait mode",8:"Landscape mode"},MeteringMode:{0:"Unknown",1:"Average",2:"CenterWeightedAverage",3:"Spot",4:"MultiSpot",5:"Pattern",6:"Partial",255:"Other"},LightSource:{0:"Unknown",1:"Daylight",2:"Fluorescent",3:"Tungsten (incandescent light)",4:"Flash",9:"Fine weather",10:"Cloudy weather",11:"Shade",12:"Daylight fluorescent (D 5700 - 7100K)",13:"Day white fluorescent (N 4600 - 5400K)",14:"Cool white fluorescent (W 3900 - 4500K)",15:"White fluorescent (WW 3200 - 3700K)",17:"Standard light A",18:"Standard light B",19:"Standard light C",20:"D55",21:"D65",22:"D75",23:"D50",24:"ISO studio tungsten",255:"Other"},Flash:{0:"Flash did not fire",1:"Flash fired",5:"Strobe return light not detected",7:"Strobe return light detected",9:"Flash fired, compulsory flash mode",13:"Flash fired, compulsory flash mode, return light not detected",15:"Flash fired, compulsory flash mode, return light detected",16:"Flash did not fire, compulsory flash mode",24:"Flash did not fire, auto mode",25:"Flash fired, auto mode",29:"Flash fired, auto mode, return light not detected",31:"Flash fired, auto mode, return light detected",32:"No flash function",65:"Flash fired, red-eye reduction mode",69:"Flash fired, red-eye reduction mode, return light not detected",71:"Flash fired, red-eye reduction mode, return light detected",73:"Flash fired, compulsory flash mode, red-eye reduction mode",77:"Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected",79:"Flash fired, compulsory flash mode, red-eye reduction mode, return light detected",89:"Flash fired, auto mode, red-eye reduction mode",93:"Flash fired, auto mode, return light not detected, red-eye reduction mode",95:"Flash fired, auto mode, return light detected, red-eye reduction mode"},SensingMethod:{1:"Not defined",2:"One-chip color area sensor",3:"Two-chip color area sensor",4:"Three-chip color area sensor",5:"Color sequential area sensor",7:"Trilinear sensor",8:"Color sequential linear sensor"},SceneCaptureType:{0:"Standard",1:"Landscape",2:"Portrait",3:"Night scene"},SceneType:{1:"Directly photographed"},CustomRendered:{0:"Normal process",1:"Custom process"},WhiteBalance:{0:"Auto white balance",1:"Manual white balance"},GainControl:{0:"None",1:"Low gain up",2:"High gain up",3:"Low gain down",4:"High gain down"},Contrast:{0:"Normal",1:"Soft",2:"Hard"},Saturation:{0:"Normal",1:"Low saturation",2:"High saturation"},Sharpness:{0:"Normal",1:"Soft",2:"Hard"},SubjectDistanceRange:{0:"Unknown",1:"Macro",2:"Close view",3:"Distant view"},FileSource:{3:"DSC"},Components:{0:"",1:"Y",2:"Cb",3:"Cr",4:"R",5:"G",6:"B"}},S={120:"caption",110:"credit",25:"keywords",55:"dateCreated",80:"byline",85:"bylineTitle",122:"captionWriter",105:"headline",116:"copyright",15:"category"};m.getData=function(e,t){return(e instanceof Image||e instanceof HTMLImageElement)&&!e.complete?!1:(n(e)?t&&t.call(e):s(e,t),!0)},m.getTag=function(e,t){return n(e)?e.exifdata[t]:void 0},m.getAllTags=function(e){if(!n(e)){ return{}; }var t,r=e.exifdata,i={};for(t in r){ r.hasOwnProperty(t)&&(i[t]=r[t]); }return i},m.pretty=function(e){if(!n(e)){ return""; }var t,r=e.exifdata,i="";for(t in r){ r.hasOwnProperty(t)&&(i+="object"==typeof r[t]?r[t]instanceof Number?t+" : "+r[t]+" ["+r[t].numerator+"/"+r[t].denominator+"]\r\n":t+" : ["+r[t].length+" values]\r\n":t+" : "+r[t]+"\r\n"); }return i},m.readFromBinaryFile=function(e){return u(e)},r=[],i=function(){return m}.apply(t,r),!(void 0!==i&&(e.exports=i));}).call(this);},function(e,t,n){var r,i;!function(){function n(e){var t=e.naturalWidth,n=e.naturalHeight;if(t*n>1048576){var r=document.createElement("canvas");r.width=r.height=1;var i=r.getContext("2d");return i.drawImage(e,-t+1,0),0===i.getImageData(0,0,1,1).data[3]}return!1}function o(e,t,n){var r=document.createElement("canvas");r.width=1,r.height=n;var i=r.getContext("2d");i.drawImage(e,0,0);for(var o=i.getImageData(0,0,1,n).data,a=0,s=n,u=n;u>a;){var c=o[4*(u-1)+3];0===c?s=u:a=u,u=s+a>>1;}var l=u/n;return 0===l?1:l}function a(e,t,n){var r=document.createElement("canvas");return s(e,r,t,n),r.toDataURL("image/jpeg",t.quality||.8)}function s(e,t,r,i){var a=e.naturalWidth,s=e.naturalHeight,c=r.width,l=r.height,f=t.getContext("2d");f.save(),u(t,f,c,l,r.orientation);var d=n(e);d&&(a/=2,s/=2);var h=1024,g=document.createElement("canvas");g.width=g.height=h;for(var p=g.getContext("2d"),m=i?o(e,a,s):1,w=Math.ceil(h*c/a),v=Math.ceil(h*l/s/m),y=0,b=0;s>y;){for(var S=0,I=0;a>S;){ p.clearRect(0,0,h,h),p.drawImage(e,-S,-y),f.drawImage(g,0,0,h,h,I,b,w,v),S+=h,I+=w; }y+=h,b+=v;}f.restore(),g=p=null;}function u(e,t,n,r,i){switch(i){case 5:case 6:case 7:case 8:e.width=r,e.height=n;break;default:e.width=n,e.height=r;}switch(i){case 2:t.translate(n,0),t.scale(-1,1);break;case 3:t.translate(n,r),t.rotate(Math.PI);break;case 4:t.translate(0,r),t.scale(1,-1);break;case 5:t.rotate(.5*Math.PI),t.scale(1,-1);break;case 6:t.rotate(.5*Math.PI),t.translate(0,-r);break;case 7:t.rotate(.5*Math.PI),t.translate(n,-r),t.scale(-1,1);break;case 8:t.rotate(-.5*Math.PI),t.translate(-n,0);}}function c(e){if(window.Blob&&e instanceof Blob){var t=new Image,n=window.URL&&window.URL.createObjectURL?window.URL:window.webkitURL&&window.webkitURL.createObjectURL?window.webkitURL:null;if(!n){ throw Error("No createObjectURL function found to create blob url"); }t.src=n.createObjectURL(e),this.blob=e,e=t;}if(!e.naturalWidth&&!e.naturalHeight){var r=this;e.onload=function(){var e=r.imageLoadListeners;if(e){r.imageLoadListeners=null;for(var t=0,n=e.length;n>t;t++){ e[t](); }}},this.imageLoadListeners=[];}this.srcImage=e;}c.prototype.render=function(e,t,n){if(this.imageLoadListeners){var r=this;return void this.imageLoadListeners.push(function(){r.render(e,t,n);})}t=t||{};var i=this.srcImage,o=i.src,u=o.length,c=i.naturalWidth,l=i.naturalHeight,f=t.width,d=t.height,h=t.maxWidth,g=t.maxHeight,p=this.blob&&"image/jpeg"===this.blob.type||0===o.indexOf("data:image/jpeg")||o.indexOf(".jpg")===u-4||o.indexOf(".jpeg")===u-5;f&&!d?d=l*f/c<<0:d&&!f?f=c*d/l<<0:(f=c,d=l),h&&f>h&&(f=h,d=l*f/c<<0),g&&d>g&&(d=g,f=c*d/l<<0);var m={width:f,height:d};for(var w in t){ m[w]=t[w]; }var v=e.tagName.toLowerCase();"img"===v?e.src=a(this.srcImage,m,p):"canvas"===v&&s(this.srcImage,e,m,p),"function"==typeof this.onrender&&this.onrender(e),n&&n();},r=[],i=function(){return c}.apply(t,r),!(void 0!==i&&(e.exports=i));}();},function(e,t){function n(e){function t(e){for(var t=[16,11,10,16,24,40,51,61,12,12,14,19,26,58,60,55,14,13,16,24,40,57,69,56,14,17,22,29,51,87,80,62,18,22,37,56,68,109,103,77,24,35,55,64,81,104,113,92,49,64,78,87,103,121,120,101,72,92,95,98,112,100,103,99],n=0;64>n;n++){var r=F((t[n]*e+50)/100);1>r?r=1:r>255&&(r=255),D[N[n]]=r;}for(var i=[17,18,24,47,99,99,99,99,18,21,26,66,99,99,99,99,24,26,56,99,99,99,99,99,47,66,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99],o=0;64>o;o++){var a=F((i[o]*e+50)/100);1>a?a=1:a>255&&(a=255),x[N[o]]=a;}for(var s=[1,1.387039845,1.306562965,1.175875602,1,.785694958,.5411961,.275899379],u=0,c=0;8>c;c++){ for(var l=0;8>l;l++){ U[u]=1/(D[N[u]]*s[c]*s[l]*8),C[u]=1/(x[N[u]]*s[c]*s[l]*8),u++; } }}function n(e,t){for(var n=0,r=0,i=new Array,o=1;16>=o;o++){for(var a=1;a<=e[o];a++){ i[t[r]]=[],i[t[r]][0]=n,i[t[r]][1]=o,r++,n++; }n*=2;}return i}function r(){y=n(W,H),b=n(V,X),S=n(z,q),I=n(Q,Y);}function i(){for(var e=1,t=2,n=1;15>=n;n++){for(var r=e;t>r;r++){ A[32767+r]=n,T[32767+r]=[],T[32767+r][1]=n,T[32767+r][0]=r; }for(var i=-(t-1);-e>=i;i++){ A[32767+i]=n,T[32767+i]=[],T[32767+i][1]=n,T[32767+i][0]=t-1+i; }e<<=1,t<<=1;}}function o(){for(var e=0;256>e;e++){ k[e]=19595*e,k[e+256>>0]=38470*e,k[e+512>>0]=7471*e+32768,k[e+768>>0]=-11059*e,k[e+1024>>0]=-21709*e,k[e+1280>>0]=32768*e+8421375,k[e+1536>>0]=-27439*e,k[e+1792>>0]=-5329*e; }}function a(e){for(var t=e[0],n=e[1]-1;n>=0;){ t&1<<n&&(G|=1<<O),n--,O--,0>O&&(255==G?(s(255),s(0)):s(G),O=7,G=0); }}function s(e){M.push(j[e]);}function u(e){s(e>>8&255),s(255&e);}function c(e,t){var n,r,i,o,a,s,u,c,l,f=0;var d=8,h=64;for(l=0;d>l;++l){n=e[f],r=e[f+1],i=e[f+2],o=e[f+3],a=e[f+4],s=e[f+5],u=e[f+6],c=e[f+7];var g=n+c,p=n-c,m=r+u,w=r-u,v=i+s,y=i-s,b=o+a,S=o-a,I=g+b,P=g-b,F=m+v,D=m-v;e[f]=I+F,e[f+4]=I-F;var x=.707106781*(D+P);e[f+2]=P+x,e[f+6]=P-x,I=S+y,F=y+w,D=w+p;var U=.382683433*(I-D),C=.5411961*I+U,T=1.306562965*D+U,A=.707106781*F,R=p+A,M=p-A;e[f+5]=M+C,e[f+3]=M-C,e[f+1]=R+T,e[f+7]=R-T,f+=8;}for(f=0,l=0;d>l;++l){n=e[f],r=e[f+8],i=e[f+16],o=e[f+24],a=e[f+32],s=e[f+40],u=e[f+48],c=e[f+56];var G=n+c,O=n-c,_=r+u,B=r-u,E=i+s,j=i-s,k=o+a,N=o-a,W=G+k,H=G-k,z=_+E,q=_-E;e[f]=W+z,e[f+32]=W-z;var V=.707106781*(q+H);e[f+16]=H+V,e[f+48]=H-V,W=N+j,z=j+B,q=B+O;var X=.382683433*(W-q),Q=.5411961*W+X,Y=1.306562965*q+X,K=.707106781*z,J=O+K,Z=O-K;e[f+40]=Z+Q,e[f+24]=Z-Q,e[f+8]=J+Y,e[f+56]=J-Y,f++;}var $;for(l=0;h>l;++l){ $=e[l]*t[l],L[l]=$>0?$+.5|0:$-.5|0; }return L}function l(){u(65504),u(16),s(74),s(70),s(73),s(70),s(0),s(1),s(1),s(0),u(1),u(1),s(0),s(0);}function f(e,t){u(65472),u(17),s(8),u(t),u(e),s(3),s(1),s(17),s(0),s(2),s(17),s(1),s(3),s(17),s(1);}function d(){u(65499),u(132),s(0);for(var e=0;64>e;e++){ s(D[e]); }s(1);for(var t=0;64>t;t++){ s(x[t]); }}function h(){u(65476),u(418),s(0);for(var e=0;16>e;e++){ s(W[e+1]); }for(var t=0;11>=t;t++){ s(H[t]); }s(16);for(var n=0;16>n;n++){ s(z[n+1]); }for(var r=0;161>=r;r++){ s(q[r]); }s(1);for(var i=0;16>i;i++){ s(V[i+1]); }for(var o=0;11>=o;o++){ s(X[o]); }s(17);for(var a=0;16>a;a++){ s(Q[a+1]); }for(var c=0;161>=c;c++){ s(Y[c]); }}function g(){u(65498),u(12),s(3),s(1),s(0),s(2),s(17),s(3),s(17),s(0),s(63),s(0);}function p(e,t,n,r,i){var o,s=i[0],u=i[240];var l=16,f=63,d=64;for(var h=c(e,t),g=0;d>g;++g){ R[N[g]]=h[g]; }var p=R[0]-n;n=R[0],0==p?a(r[0]):(o=32767+p,a(r[A[o]]),a(T[o]));for(var m=63;m>0&&0==R[m];m--){  }if(0==m){ return a(s),n; }for(var w,v=1;m>=v;){for(var y=v;0==R[v]&&m>=v;++v){  }var b=v-y;if(b>=l){w=b>>4;for(var S=1;w>=S;++S){ a(u); }b=15&b;}o=32767+R[v],a(i[(b<<4)+A[o]]),a(T[o]),v++;}return m!=f&&a(s),n}function m(){for(var e=String.fromCharCode,t=0;256>t;t++){ j[t]=e(t); }}function w(e){if(0>=e&&(e=1),e>100&&(e=100),P!=e){var n=0;n=50>e?Math.floor(5e3/e):Math.floor(200-2*e),t(n),P=e;}}function v(){var t=(new Date).getTime();e||(e=50),m(),r(),i(),o(),w(e);(new Date).getTime()-t;}var y,b,S,I,P,F=(Math.round,Math.floor),D=new Array(64),x=new Array(64),U=new Array(64),C=new Array(64),T=new Array(65535),A=new Array(65535),L=new Array(64),R=new Array(64),M=[],G=0,O=7,_=new Array(64),B=new Array(64),E=new Array(64),j=new Array(256),k=new Array(2048),N=[0,1,5,6,14,15,27,28,2,4,7,13,16,26,29,42,3,8,12,17,25,30,41,43,9,11,18,24,31,40,44,53,10,19,23,32,39,45,52,54,20,22,33,38,46,51,55,60,21,34,37,47,50,56,59,61,35,36,48,49,57,58,62,63],W=[0,0,1,5,1,1,1,1,1,1,0,0,0,0,0,0,0],H=[0,1,2,3,4,5,6,7,8,9,10,11],z=[0,0,2,1,3,3,2,4,3,5,5,4,4,0,0,1,125],q=[1,2,3,0,4,17,5,18,33,49,65,6,19,81,97,7,34,113,20,50,129,145,161,8,35,66,177,193,21,82,209,240,36,51,98,114,130,9,10,22,23,24,25,26,37,38,39,40,41,42,52,53,54,55,56,57,58,67,68,69,70,71,72,73,74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,121,122,131,132,133,134,135,136,137,138,146,147,148,149,150,151,152,153,154,162,163,164,165,166,167,168,169,170,178,179,180,181,182,183,184,185,186,194,195,196,197,198,199,200,201,202,210,211,212,213,214,215,216,217,218,225,226,227,228,229,230,231,232,233,234,241,242,243,244,245,246,247,248,249,250],V=[0,0,3,1,1,1,1,1,1,1,1,1,0,0,0,0,0],X=[0,1,2,3,4,5,6,7,8,9,10,11],Q=[0,0,2,1,2,4,4,3,4,7,5,4,4,0,1,2,119],Y=[0,1,2,3,17,4,5,33,49,6,18,65,81,7,97,113,19,34,50,129,8,20,66,145,161,177,193,9,35,51,82,240,21,98,114,209,10,22,36,52,225,37,241,23,24,25,26,38,39,40,41,42,53,54,55,56,57,58,67,68,69,70,71,72,73,74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,121,122,130,131,132,133,134,135,136,137,138,146,147,148,149,150,151,152,153,154,162,163,164,165,166,167,168,169,170,178,179,180,181,182,183,184,185,186,194,195,196,197,198,199,200,201,202,210,211,212,213,214,215,216,217,218,226,227,228,229,230,231,232,233,234,242,243,244,245,246,247,248,249,250];this.encode=function(e,t,n){var r=(new Date).getTime();t&&w(t),M=new Array,G=0,O=7,u(65496),l(),d(),f(e.width,e.height),h(),g();var i=0,o=0,s=0;G=0,O=7,this.encode.displayName="_encode_";for(var c,m,v,P,F,D,x,T,A,L=e.data,R=e.width,j=e.height,N=4*R,W=0;j>W;){for(c=0;N>c;){for(F=N*W+c,D=F,x=-1,T=0,A=0;64>A;A++){ T=A>>3,x=4*(7&A),D=F+T*N+x,W+T>=j&&(D-=N*(W+1+T-j)),c+x>=N&&(D-=c+x-N+4),m=L[D++],v=L[D++],P=L[D++],_[A]=(k[m]+k[v+256>>0]+k[P+512>>0]>>16)-128,B[A]=(k[m+768>>0]+k[v+1024>>0]+k[P+1280>>0]>>16)-128,E[A]=(k[m+1280>>0]+k[v+1536>>0]+k[P+1792>>0]>>16)-128; }i=p(_,U,i,y,S),o=p(B,C,o,b,I),s=p(E,C,s,b,I),c+=32;}W+=8;}if(O>=0){var H=[];H[1]=O+1,H[0]=(1<<O+1)-1,a(H);}if(u(65497),n){for(var z=M.length,q=new Uint8Array(z),V=0;z>V;V++){ q[V]=M[V].charCodeAt(); }M=[];(new Date).getTime()-r;return q}var X="data:image/jpeg;base64,"+btoa(M.join(""));M=[];(new Date).getTime()-r;return X},v();}e.exports=n;},function(e,t,n){function r(e,t){var n=this;if(!e){ throw new Error("没有收到图片，可能的解决方案：https://github.com/think2011/localResizeIMG/issues/7"); }t=t||{},n.defaults={width:null,height:null,fieldName:"file",quality:.7},n.file=e;for(var r in t){ t.hasOwnProperty(r)&&(n.defaults[r]=t[r]); }return this.init()}function i(e){var t=null;return t=e?[].filter.call(document.scripts,function(t){return-1!==t.src.indexOf(e)})[0]:document.scripts[document.scripts.length-1],t?t.src.substr(0,t.src.lastIndexOf("/")):null}function o(e){var t;t=e.split(",")[0].indexOf("base64")>=0?atob(e.split(",")[1]):unescape(e.split(",")[1]);for(var n=e.split(",")[0].split(":")[1].split(";")[0],r=new Uint8Array(t.length),i=0;i<t.length;i++){ r[i]=t.charCodeAt(i); }return new s.Blob([r.buffer],{type:n})}n.p=i("lrz")+"/",window.URL=window.URL||window.webkitURL;var a=n(1),s=n(4),u=n(5),c=function(e){var t=/OS (\d)_.* like Mac OS X/g.exec(e),n=/Android (\d.*?);/g.exec(e)||/Android\/(\d.*?) /g.exec(e);return{oldIOS:t?+t.pop()<8:!1,oldAndroid:n?+n.pop().substr(0,3)<4.5:!1,iOS:/\(i[^;]+;( U;)? CPU.+Mac OS X/.test(e),android:/Android/g.test(e),mQQBrowser:/MQQBrowser/g.test(e)}}(navigator.userAgent);r.prototype.init=function(){var e=this,t=e.file,n="string"==typeof t,r=/^data:/.test(t),i=new Image,u=document.createElement("canvas"),c=n?t:URL.createObjectURL(t);if(e.img=i,e.blob=c,e.canvas=u,n?e.fileName=r?"base64.jpg":t.split("/").pop():e.fileName=t.name,!document.createElement("canvas").getContext){ throw new Error("浏览器不支持canvas"); }return new a(function(n,a){i.onerror=function(){var e=new Error("加载图片文件失败");throw a(e),e},i.onload=function(){e._getBase64().then(function(e){if(e.length<10){var t=new Error("生成base64失败");throw a(t),t}return e}).then(function(r){var i=null;"object"==typeof e.file&&r.length>e.file.size?(i=new FormData,t=e.file):(i=new s.FormData,t=o(r)),i.append(e.defaults.fieldName,t,e.fileName.replace(/\..+/g,".jpg")),n({formData:i,fileLen:+t.size,base64:r,base64Len:r.length,origin:e.file,file:t});for(var a in e){ e.hasOwnProperty(a)&&(e[a]=null); }URL.revokeObjectURL(e.blob);});},!r&&(i.crossOrigin="*"),i.src=c;})},r.prototype._getBase64=function(){var e=this,t=e.img,n=e.file,r=e.canvas;return new a(function(i){try{u.getData("object"==typeof n?n:t,function(){e.orientation=u.getTag(this,"Orientation"),e.resize=e._getResize(),e.ctx=r.getContext("2d"),r.width=e.resize.width,r.height=e.resize.height,e.ctx.fillStyle="#fff",e.ctx.fillRect(0,0,r.width,r.height),c.oldIOS?e._createBase64ForOldIOS().then(i):e._createBase64().then(i);});}catch(o){throw new Error(o)}})},r.prototype._createBase64ForOldIOS=function(){var e=this,t=e.img,r=e.canvas,i=e.defaults,o=e.orientation;return new a(function(e){!function(){var a=[n(6)];(function(n){var a=new n(t);"5678".indexOf(o)>-1?a.render(r,{width:r.height,height:r.width,orientation:o}):a.render(r,{width:r.width,height:r.height,orientation:o}),e(r.toDataURL("image/jpeg",i.quality));}).apply(null,a);}();})},r.prototype._createBase64=function(){var e=this,t=e.resize,r=e.img,i=e.canvas,o=e.ctx,s=e.defaults,u=e.orientation;switch(u){case 3:o.rotate(180*Math.PI/180),o.drawImage(r,-t.width,-t.height,t.width,t.height);break;case 6:o.rotate(90*Math.PI/180),o.drawImage(r,0,-t.width,t.height,t.width);break;case 8:o.rotate(270*Math.PI/180),o.drawImage(r,-t.height,0,t.height,t.width);break;case 2:o.translate(t.width,0),o.scale(-1,1),o.drawImage(r,0,0,t.width,t.height);break;case 4:o.translate(t.width,0),o.scale(-1,1),o.rotate(180*Math.PI/180),o.drawImage(r,-t.width,-t.height,t.width,t.height);break;case 5:o.translate(t.width,0),o.scale(-1,1),o.rotate(90*Math.PI/180),o.drawImage(r,0,-t.width,t.height,t.width);break;case 7:o.translate(t.width,0),o.scale(-1,1),o.rotate(270*Math.PI/180),o.drawImage(r,-t.height,0,t.height,t.width);break;default:o.drawImage(r,0,0,t.width,t.height);}return new a(function(e){c.oldAndroid||c.mQQBrowser||!navigator.userAgent?!function(){var t=[n(7)];(function(t){var n=new t,r=o.getImageData(0,0,i.width,i.height);e(n.encode(r,100*s.quality));}).apply(null,t);}():e(i.toDataURL("image/jpeg",s.quality));})},r.prototype._getResize=function(){var e=this,t=e.img,n=e.defaults,r=n.width,i=n.height,o=e.orientation,a={width:t.width,height:t.height};if("5678".indexOf(o)>-1&&(a.width=t.height,a.height=t.width),a.width<r||a.height<i){ return a; }var s=a.width/a.height;for(r&&i?s>=r/i?a.width>r&&(a.width=r,a.height=Math.ceil(r/s)):a.height>i&&(a.height=i,a.width=Math.ceil(i*s)):r?r<a.width&&(a.width=r,a.height=Math.ceil(r/s)):i&&i<a.height&&(a.width=Math.ceil(i*s),a.height=i);a.width>=3264||a.height>=2448;){ a.width*=.8,a.height*=.8; }return a},window.lrz=function(e,t){return new r(e,t)},window.lrz.version="4.9.40",
e.exports=window.lrz;}])});
});

var template$3 = "<div> <div v-show=\"upload.status=='ready'\"> <input type=\"text\" v-model=\"imageUrl\" maxlength=\"255\" :placeholder=\"$parent.locale['please enter a url']\"> <button type=\"button\" @click=\"insertImageUrl\">{{$parent.locale.save}}</button> <input type=\"file\" ref=\"file\" style=\"display: none !important\" @change=\"process\" accept=\"image/png,image/jpeg,image/gif,image/jpg\"> <button type=\"button\" @click=\"pick\">{{$parent.locale.upload}}</button> </div> <div v-if=\"upload.status=='progress'\"> {{$parent.locale.progress}}:{{upload.progressComputable ? $parent.locale.unknown : upload.complete}} </div> <div v-if=\"upload.status=='success'\"> {{$parent.locale[\"please wait\"]}}... </div> <div v-if=\"upload.status=='error'\"> {{$parent.locale.error}}:{{upload.errorMsg}} <button type=\"button\" @click=\"reset\">{{$parent.locale.reset}}</button> </div> <div v-if=\"upload.status=='abort'\"> {{$parent.locale.upload}}&nbsp;{{$parent.locale.abort}}, <button type=\"button\" @click=\"reset\">{{$parent.locale.reset}}</button> </div> </div> ";

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$3 = {
    template: template$3,
    data: function data() {
        return {
            imageUrl: '',
            upload: {
                status: 'ready', // progress,success,error,abort
                errorMsg: null,
                progressComputable: false,
                complete: 0
            }
        }
    },
    methods: {
        reset: function reset(){
            this.upload.status = 'ready';
        },
        insertImageUrl: function insertImageUrl() {
            if (!this.imageUrl) {
                return
            }
            this.$parent.execCommand(Command.INSERT_IMAGE, this.imageUrl);
            this.imageUrl = null;
        },
        pick: function pick() {
            this.$refs.file.click();
        },
        setUploadError: function setUploadError(msg){
            this.upload.status = 'error';
            this.upload.errorMsg = msg;
        },
        process: function process() {
            var this$1 = this;

            var component = this;
            var config = this.$options.module.config;
            // compatibility with older format
            // {
            //     server: null,
            //     fieldName: 'image',
            //     compress: true,
            //     width: 1600,
            //     height: 1600,
            //     quality: 80
            // }
            // ----------- divider ----------------
            // {
            //     upload: {
            //         url: null,
            //         headers: {},
            //         params: {},
            //         fieldName: {}
            //     },
            //     compress: {
            //         width: 1600,
            //         height: 1600,
            //         quality: 80
            //     },
            // }

            if (!config.upload && typeof config.server === 'string') {
                config.upload = {url: config.server};
            }
            if (config.upload && !config.upload.url) {
                config.upload = null;
            }
            if (config.upload && typeof config.fieldName === 'string') {
                config.upload.fieldName = config.fieldName;
            }

            if (typeof config.compress === 'boolean') {
                config.compress = {
                    width: config.width,
                    height: config.height,
                    quality: config.quality
                };
            }

            var file = this.$refs.file.files[0];
            if (file.size > config.sizeLimit) {
                this.setUploadError(this.$parent.locale['exceed size limit']);
                return
            }
            this.$refs.file.value = null;

            if (config.compress) {
                config.compress.fieldName = config.upload && config.upload.fieldName
                    ? config.upload.fieldName : 'image';
                lrz_all_bundle(file, config.compress).then(function (rst) {
                    if (config.upload) {
                        // component.uploadToServer(rst.file);
                        component.uploadToServer(file);
                    } else {
                        component.insertBase64(rst.base64);
                    }
                }).catch(function (err) {
                    this$1.setUploadError(err.toString());
                });
                return
            }
            // 不需要压缩
            // base64
            if (!config.upload) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    component.insertBase64(e.target.result);
                };
                reader.readAsDataURL(file);
                return
            }
            // 上传服务器
            component.uploadToServer(file);
        },
        insertBase64: function insertBase64(data) {
            this.$parent.execCommand(Command.INSERT_IMAGE, data);
        },
        uploadToServer: function uploadToServer(file) {
            var this$1 = this;

            var config = this.$options.module.config;

            var formData = new FormData();
            formData.append(config.upload.fieldName || 'image', file);

            if (typeof config.upload.params === 'object') {
                Object.keys(config.upload.params).forEach(function (key) {
                    var value = config.upload.params[key];
                    if (Array.isArray(value)) {
                        value.forEach(function (v) {
                            formData.append(key, v);
                        });
                    } else {
                        formData.append(key, value);
                    }
                });
            }

            var xhr = new XMLHttpRequest();

            xhr.onprogress = function (e) {
                this$1.upload.status = 'progress';
                if (e.lengthComputable) {
                    this$1.upload.progressComputable = true;
                    var percentComplete = e.loaded / e.total;
                    this$1.upload.complete = (percentComplete * 100).toFixed(2);
                } else {
                    this$1.upload.progressComputable = false;
                }
            };

            xhr.onload = function () {
                if (xhr.status >= 300) {
                    this$1.setUploadError(("request error,code " + (xhr.status)));
                    return
                }

                try {
                    var url = config.uploadHandler(xhr.responseText);
                    if (url) {
                        this$1.$parent.execCommand(Command.INSERT_IMAGE, url);
                    }
                } catch (err) {
                    this$1.setUploadError(err.toString());
                } finally {
                    this$1.upload.status = 'ready';
                }
            };

            xhr.onerror = function () {
                // find network info in brower tools
                this$1.setUploadError('request error');
            };

            xhr.onabort = function () {
                this$1.upload.status = 'abort';
            };

            xhr.open('POST', config.upload.url);
            if (typeof config.upload.headers === 'object') {
                Object.keys(config.upload.headers).forEach(function (k) {
                    xhr.setRequestHeader(k, config.upload.headers[k]);
                });
            }
            xhr.send(formData);
        }
    }
};

/**
 * insert image
 * Created by peak on 16/8/18.
 */
var image = {
    name: 'image',
    icon: 'fa fa-file-image-o',
    i18n: 'image',
    config: {
        // server: null,
        // fieldName: 'image',
        // compress: true,
        // width: 1600,
        // height: 1600,
        // quality: 80,
        sizeLimit: 512 * 1024,// 512k
        // upload: {
        //     url: null,
        //     headers: {},
        //     params: {},
        //     fieldName: {}
        // },
        compress: {
            width: 1600,
            height: 1600,
            quality: 80
        },
        uploadHandler: function uploadHandler(responseText){
            var json = JSON.parse(responseText);
            return json.ok ? json.data : null
        }
    },
    dashboard: dashboard$3
};

var template$4 = "<div> <h3 style=\"text-align: center\">Vue-html5-editor&nbsp;{{version}}</h3> <p style=\"text-align: center\"> repository: <a href=\"https://github.com/PeakTai/vue-html5-editor\" target=\"_blank\"> https://github.com/PeakTai/vue-html5-editor </a> </p> </div> ";

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$4 = {
    template: template$4,
    data: function data(){
        return {
            version: "1.1.1"
        }
    }
};

/**
 * editor info
 * Created by peak on 16/8/18.
 */
var info = {
    name: 'info',
    icon: 'fa fa-info',
    i18n: 'info',
    // handler () {
    //
    // },
    // init (editor) {
    //
    // },
    // destroyed(editor){
    //
    // },
    dashboard: dashboard$4
};

var template$5 = "<form @submit.prevent=\"createLink\"> <input type=\"text\" :placeholder=\"$parent.locale['please enter a url']\" v-model=\"url\" maxlength=\"1024\"> <button type=\"submit\">{{$parent.locale[\"create link\"]}}</button> </form>";

var dashboard$5 = {
    template: template$5,
    data: function data(){
        return {url: null}
    },
    methods: {
        createLink: function createLink(){
            if (!this.url) {
                return
            }
            this.$parent.execCommand('createLink', this.url);
            this.url = null;
        }
    }
};

/**
 * create link
 * Created by peak on 16/8/18.
 */
var link = {
    name: 'link',
    icon: 'fa fa-chain',
    i18n: 'link',
    dashboard: dashboard$5
};

var template$6 = "<div> <button type=\"button\" @click=\"$parent.execCommand('insertOrderedList')\"> {{$parent.locale[\"ordered list\"]}} </button> <button type=\"button\" @click=\"$parent.execCommand('insertUnorderedList')\"> {{$parent.locale[\"unordered list\"]}} </button> </div>";

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$6 = {
    template: template$6
};

/**
 * list,ul,ol
 * Created by peak on 16/8/18.
 */
var list = {
    name: 'list',
    icon: 'fa fa-list',
    i18n: 'list',
    dashboard: dashboard$6
};

var template$7 = "<form @submit.prevent=\"insertTable\"> <label> {{$parent.locale[\"row count\"]}} <input type=\"number\" style=\"width: 60px\" maxlength=\"2\" min=\"2\" max=\"10\" v-model=\"rows\"> </label> <label> {{$parent.locale[\"column count\"]}} <input type=\"number\" style=\"width: 60px\" maxlength=\"2\" min=\"2\" max=\"10\" v-model=\"cols\"> </label> <button type=\"submit\">{{$parent.locale.save}}</button> </form>";

/**
 * Created by peak on 2017/2/10.
 */
var dashboard$7 = {
    template: template$7,
    data: function data(){
        return {
            rows: 2,
            cols: 2,
            hasHead: false,
            striped: false,
            hover: false
        }
    },
    methods: {
        insertTable: function insertTable(){
            if (this.rows < 2 || this.rows > 10) {
                return
            }
            if (this.cols < 2 || this.cols > 10) {
                return
            }
            var table = '<table style="border-spacing: 0px; border-collapse: collapse; width: 100%; max-width: 100%; margin-bottom: 0px; border: 1px solid rgb(221, 221, 221); color: rgb(51, 51, 51); font-size: 14px; line-height: 20px; background-color: transparent;"><tbody>';
            for (var i = 0; i < this.rows; i++) {
                table += '<tr>';
                for (var j = 0; j < this.cols; j++) {
                    table += '<td style="padding: 8px; line-height: 1.42857; vertical-align: top; border: 1px solid rgb(221, 221, 221);">&nbsp;</td>';
                }
                table += '</tr>';
            }
            table += '</tbody></table>';
            this.$parent.execCommand('insertHTML', table);
        }
    }
};

/**
 * insert table
 * Created by peak on 16/8/18.
 */
var table = {
    // can not named table
    // dashboard.html will add to editor as a child component and named as module name
    // Do not use built-in or reserved HTML elements as component id
    name: 'tabulation',
    icon: 'fa fa-table',
    i18n: 'table',
    dashboard: dashboard$7
};

var template$8 = "<div> <button type=\"button\" @click=\"$parent.execCommand('bold')\">{{$parent.locale[\"bold\"]}}</button> <button type=\"button\" @click=\"$parent.execCommand('italic')\">{{$parent.locale[\"italic\"]}}</button> <button type=\"button\" @click=\"$parent.execCommand('underline')\">{{$parent.locale[\"underline\"]}}</button> <button type=\"button\" @click=\"$parent.execCommand('strikeThrough')\">{{$parent.locale[\"strike through\"]}} </button> <button type=\"button\" @click=\"$parent.execCommand('subscript')\">{{$parent.locale[\"subscript\"]}}</button> <button type=\"button\" @click=\"$parent.execCommand('superscript')\">{{$parent.locale[\"superscript\"]}}</button> </div> ";

var dashboard$8 = {
    template: template$8
};

/**
 * text,set the text bold or italic or underline or with strike through or subscript or superscript
 * Created by peak on 16/8/18.
 */
var text = {
    name: 'text',
    icon: 'fa fa-pencil',
    i18n: 'text',
    dashboard: dashboard$8
};

/**
 * undo
 * Created by peak on 16/8/20.
 */
var undo = {
    name: 'undo',
    icon: 'fa-undo fa',
    i18n: 'undo',
    handler: function handler(editor) {
        editor.execCommand('undo');
    }
};

/**
 * unlink
 * Created by peak on 16/8/18.
 */
var unlink = {
    name: 'unlink',
    icon: 'fa fa-chain-broken',
    i18n: 'unlink',
    handler: function handler(editor) {
        editor.execCommand('unlink');
    }
};

/**
 * build-in moduls
 * Created by peak on 2016/11/1.
 */
var buildInModules = [
    text,
    color,
    font,
    align,
    list,
    link,
    unlink,
    table,
    image,
    hr,
    eraser,
    undo,
    fullScreen$1,
    info
];

/**
 * Created by peak on 2017/2/15.
 */
/**
 * add every elements of extArr to sourceArr.
 * @param sourceArr
 * @param extArr
 */
var mergeArray = function (sourceArr, extArr) {
    // note: Array.prototype.push.apply(arr1,arr2) is unreliable
    extArr.forEach(function (el) {
        sourceArr.push(el);
    });
};

/**
 * find all the descendant text nodes of a element
 * @param ancestor
 */
var getDescendantTextNodes = function (ancestor) {
    if (ancestor.nodeType === Node.TEXT_NODE) {
        return [ancestor]
    }
    var textNodes = [];
    if (!ancestor.hasChildNodes()) {
        return textNodes
    }
    var childNodes = ancestor.childNodes;
    for (var i = 0; i < childNodes.length; i++) {
        var node = childNodes[i];
        if (node.nodeType === Node.TEXT_NODE) {
            textNodes.push(node);
        } else if (node.nodeType === Node.ELEMENT_NODE) {
            mergeArray(textNodes, getDescendantTextNodes(node));
        }
    }
    return textNodes
};
/**
 * find all the descendant text nodes of an ancestor element that before the specify end element,
 * the ancestor element must contains the end element.
 * @param ancestor
 * @param endEl
 */
var getBeforeEndDescendantTextNodes = function (ancestor, endEl) {
    var textNodes = [];
    var endIndex = 0;
    for (var i = 0; i < ancestor.childNodes.length; i++) {
        if (ancestor.childNodes[i].contains(endEl)) {
            endIndex = i;
            break
        }
    }

    for (var i$1 = 0; i$1 <= endIndex; i$1++) {
        var node = ancestor.childNodes[i$1];
        if (node === endEl) {
            mergeArray(textNodes, getDescendantTextNodes(node));
        } else if (i$1 === endIndex) {
            if (node.nodeType === Node.TEXT_NODE) {
                textNodes.push(node);
            } else if (node.nodeType === Node.ELEMENT_NODE) {
                mergeArray(textNodes, getBeforeEndDescendantTextNodes(node, endEl));
            }
        } else if (node.nodeType === Node.TEXT_NODE) {
            textNodes.push(node);
        } else if (node.nodeType === Node.ELEMENT_NODE) {
            mergeArray(textNodes, getDescendantTextNodes(node));
        }
    }
    return textNodes
};
/**
 * find all the descendant text nodes of an ancestor element that after the specify start element,
 * the ancestor element must contains the start element.
 * @param ancestor
 * @param startEl
 */
var getAfterStartDescendantTextNodes = function (ancestor, startEl) {
    var textNodes = [];
    var startIndex = 0;
    for (var i = 0; i < ancestor.childNodes.length; i++) {
        if (ancestor.childNodes[i].contains(startEl)) {
            startIndex = i;
            break
        }
    }

    for (var i$1 = startIndex; i$1 < ancestor.childNodes.length; i$1++) {
        var node = ancestor.childNodes[i$1];
        if (node === startEl) {
            mergeArray(textNodes, getDescendantTextNodes(node));
        } else if (i$1 === startIndex) {
            if (node.nodeType === Node.TEXT_NODE) {
                textNodes.push(node);
            } else if (node.nodeType === Node.ELEMENT_NODE) {
                mergeArray(textNodes,
                    getAfterStartDescendantTextNodes(node, startEl));
            }
        } else if (node.nodeType === Node.TEXT_NODE) {
            textNodes.push(node);
        } else if (node.nodeType === Node.ELEMENT_NODE) {
            mergeArray(textNodes,
                getDescendantTextNodes(node));
        }
    }
    return textNodes
};


/**
 * get the closest parent block node of a text node.
 * @param node
 * @return {Node}
 */
var getParentBlockNode = function (node) {
    var blockNodeNames = ['DIV', 'P', 'SECTION', 'H1', 'H2', 'H3', 'H4', 'H5', 'H6',
        'OL', 'UL', 'LI', 'TR', 'TD', 'TH', 'TBODY', 'THEAD', 'TABLE', 'ARTICLE', 'HEADER', 'FOOTER'];
    var container = node;
    while (container) {
        if (blockNodeNames.includes(container.nodeName)) {
            break
        }
        container = container.parentNode;
    }
    return container
};

var isInlineElement = function (node) {
    var inlineNodeNames = ['A', 'ABBR', 'ACRONYM', 'B', 'CITE', 'CODE', 'EM', 'I',
        'FONT', 'IMG', 'S', 'SMALL', 'SPAN', 'STRIKE', 'STRONG', 'U', 'SUB', 'SUP'];
    return inlineNodeNames.includes(node.nodeName)
};

// for IE 11
if (!Text.prototype.contains) {
    Text.prototype.contains = function contains(otherNode) {
        return this === otherNode
    };
}


/**
 * Created by peak on 2017/2/14.
 */
var RangeHandler = function RangeHandler(range) {
    if (!range || !(range instanceof Range)) {
        throw new TypeError('cant\'t resolve range')
    }
    this.range = range;
};


/**
 * find all the text nodes in range
 */
RangeHandler.prototype.getAllTextNodesInRange = function getAllTextNodesInRange () {
    var startContainer = this.range.startContainer;
    var endContainer = this.range.endContainer;
    var rootEl = this.range.commonAncestorContainer;
    var textNodes = [];

    if (startContainer === endContainer) {
        if (startContainer.nodeType === Node.TEXT_NODE) {
            return [startContainer]
        }
        var childNodes = startContainer.childNodes;
        for (var i = this.range.startOffset; i < this.range.endOffset; i++) {
            mergeArray(textNodes, getDescendantTextNodes(childNodes[i]));
        }
        return textNodes
    }

    var startIndex = 0;
    var endIndex = 0;
    for (var i$1 = 0; i$1 < rootEl.childNodes.length; i$1++) {
        var node = rootEl.childNodes[i$1];
        if (node.contains(startContainer)) {
            startIndex = i$1;
        }
        if (node.contains(endContainer)) {
            endIndex = i$1;
        }
    }

    for (var i$2 = startIndex; i$2 <= endIndex; i$2++) {
        var node$1 = rootEl.childNodes[i$2];
        if (i$2 === startIndex) {
            if (node$1.nodeType === Node.TEXT_NODE) {
                textNodes.push(node$1);
            } else if (node$1.nodeType === Node.ELEMENT_NODE) {
                mergeArray(textNodes, getAfterStartDescendantTextNodes(node$1, startContainer));
            }
        } else if (i$2 === endIndex) {
            if (node$1.nodeType === Node.TEXT_NODE) {
                textNodes.push(node$1);
            } else if (node$1.nodeType === Node.ELEMENT_NODE) {
                mergeArray(textNodes, getBeforeEndDescendantTextNodes(node$1, endContainer));
            }
        } else if (node$1.nodeType === Node.TEXT_NODE) {
            textNodes.push(node$1);
        } else if (node$1.nodeType === Node.ELEMENT_NODE) {
            mergeArray(textNodes, getDescendantTextNodes(node$1));
        }
    }
    return textNodes
};

/**
 * execute edit command
 * @param {String} command
 * @param arg
 */
RangeHandler.prototype.execCommand = function execCommand (command, arg) {
        var this$1 = this;

    switch (command) {

        case Command.FONT_SIZE: {
            // 重新实现，改为直接修改样式
            var textNodes = this.getAllTextNodesInRange();
            if (!textNodes.length) {
                break
            }
            if (textNodes.length === 1 && textNodes[0] === this.range.startContainer
                && textNodes[0] === this.range.endContainer) {
                var textNode = textNodes[0];
                if (this.range.startOffset === 0
                    && this.range.endOffset === textNode.textContent.length) {
                    if (textNode.parentNode.childNodes.length === 1
                        && isInlineElement(textNode.parentNode)) {
                        textNode.parentNode.style.fontSize = arg;
                        break
                    }
                    var span = document.createElement('span');
                    span.style.fontSize = arg;
                    textNode.parentNode.insertBefore(span, textNode);
                    span.appendChild(textNode);
                    break
                }
                var span$1 = document.createElement('span');
                span$1.innerText = textNode.textContent.substring(
                    this.range.startOffset, this.range.endOffset);
                span$1.style.fontSize = arg;
                var frontPart = document.createTextNode(
                    textNode.textContent.substring(0, this.range.startOffset));
                textNode.parentNode.insertBefore(frontPart, textNode);
                textNode.parentNode.insertBefore(span$1, textNode);
                textNode.textContent = textNode.textContent.substring(this.range.endOffset);
                this.range.setStart(span$1, 0);
                this.range.setEnd(span$1, 1);
                break
            }

            textNodes.forEach(function (textNode) {
                if (textNode === this$1.range.startContainer) {
                    if (this$1.range.startOffset === 0) {
                        if (textNode.parentNode.childNodes.length === 1
                            && isInlineElement(textNode.parentNode)) {
                            textNode.parentNode.style.fontSize = arg;
                        } else {
                            var span$1 = document.createElement('span');
                            span$1.style.fontSize = arg;
                            textNode.parentNode.insertBefore(span$1, textNode);
                            span$1.appendChild(textNode);
                        }
                        return
                    }
                    var span$2 = document.createElement('span');
                    textNode.textContent = textNode.textContent.substring(
                        0, this$1.range.startOffset);
                    span$2.style.fontSize = arg;
                    textNode.parentNode.insertBefore(span$2, textNode);
                    this$1.range.setStart(textNode, 0);
                    return
                }
                if (textNode === this$1.range.endContainer) {
                    if (this$1.range.endOffset === textNode.textContent.length) {
                        if (textNode.parentNode.childNodes.length === 1
                            && isInlineElement(textNode.parentNode)) {
                            textNode.parentNode.style.fontSize = arg;
                        } else {
                            var span$3 = document.createElement('span');
                            span$3.style.fontSize = arg;
                            textNode.parentNode.insertBefore(span$3, textNode);
                            span$3.appendChild(textNode);
                        }
                        return
                    }
                    var span$4 = document.createElement('span');
                    textNode.textContent = textNode.textContent.substring(this$1.range.endOffset);
                    span$4.style.fontSize = arg;
                    textNode.parentNode.insertBefore(span$4, textNode);
                    span$4.appendChild(textNode);
                    this$1.range.setStart(textNode, textNode.textContent.length);
                    return
                }
                if (textNode.parentNode.childNodes.length === 1
                    && isInlineElement(textNode.parentNode)) {
                    textNode.parentNode.style.fontSize = arg;
                    return
                }

                var span = document.createElement('span');
                span.style.fontSize = arg;
                textNode.parentNode.insertBefore(span, textNode);
                span.appendChild(textNode);
            });
            break
        }
        case Command.FORMAT_BLOCK: {
            if (document.execCommand(Command.FORMAT_BLOCK, false, arg)) {
                break
            }
            // hack
            var element = document.createElement(arg);
            this.range.surroundContents(element);
            break
        }
        case Command.LINE_HEIGHT: {
            var textNodes$1 = this.getAllTextNodesInRange();
            textNodes$1.forEach(function (textNode) {
                var parentBlock = getParentBlockNode(textNode);
                if (parentBlock) {
                    parentBlock.style.lineHeight = arg;
                }
            });
            break
        }
        case Command.INSERT_HTML: {
            if (document.execCommand(Command.INSERT_HTML, false, arg)) {
                break
            }
            // hack
            var fragment = document.createDocumentFragment();
            var div = document.createElement('div');
            div.innerHTML = arg;
            if (div.hasChildNodes()) {
                for (var i = 0; i < div.childNodes.length; i++) {
                    fragment.appendChild(div.childNodes[i].cloneNode(true));
                }
            }
            this.range.deleteContents();
            this.range.insertNode(fragment);
            break
        }
        default: {
            document.execCommand(command, false, arg);
            break
        }
    }
};

__$styleInject(".vue-html5-editor,.vue-html5-editor *{box-sizing:border-box}.vue-html5-editor{font-size:14px;line-height:1.5;background-color:#fff;color:#333;border:1px solid #ddd;text-align:left;border-radius:5px;overflow:hidden}.vue-html5-editor.full-screen{position:fixed!important;top:0!important;left:0!important;bottom:0!important;right:0!important;border-radius:0}.vue-html5-editor>.toolbar{position:relative;background-color:inherit}.vue-html5-editor>.toolbar>ul{list-style:none;padding:0;margin:0;border-bottom:1px solid #ddd}.vue-html5-editor>.toolbar>ul>li{display:inline-block;cursor:pointer;text-align:center;line-height:36px;padding:0 10px}.vue-html5-editor>.toolbar>ul>li .icon{height:16px;width:16px;display:inline-block;vertical-align:middle}.vue-html5-editor>.toolbar>.dashboard{background-color:inherit;border-bottom:1px solid #ddd;padding:10px;position:absolute;top:100%;left:0;right:0;overflow:auto}.vue-html5-editor>.toolbar>.dashboard input[type=text],.vue-html5-editor>.toolbar>.dashboard input[type=number],.vue-html5-editor>.toolbar>.dashboard select{padding:6px 12px;color:inherit;background-color:transparent;border:1px solid #ddd;border-radius:5px}.vue-html5-editor>.toolbar>.dashboard input[type=text]:hover,.vue-html5-editor>.toolbar>.dashboard input[type=number]:hover,.vue-html5-editor>.toolbar>.dashboard select:hover{border-color:#bebebe}.vue-html5-editor>.toolbar>.dashboard input[type=text][disabled],.vue-html5-editor>.toolbar>.dashboard input[type=text][readonly],.vue-html5-editor>.toolbar>.dashboard input[type=number][disabled],.vue-html5-editor>.toolbar>.dashboard input[type=number][readonly],.vue-html5-editor>.toolbar>.dashboard select[disabled],.vue-html5-editor>.toolbar>.dashboard select[readonly]{background-color:#eee;opacity:1}.vue-html5-editor>.toolbar>.dashboard input[type=text][disabled],.vue-html5-editor>.toolbar>.dashboard input[type=number][disabled],.vue-html5-editor>.toolbar>.dashboard select[disabled]{cursor:not-allowed}.vue-html5-editor>.toolbar>.dashboard button{color:inherit;background-color:inherit;padding:6px 12px;white-space:nowrap;vertical-align:middle;cursor:pointer;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;border:1px solid #ddd;border-radius:5px;margin-right:4px;margin-bottom:4px}.vue-html5-editor>.toolbar>.dashboard button:hover{border-color:#bebebe}.vue-html5-editor>.toolbar>.dashboard button[disabled]{cursor:not-allowed;opacity:.68}.vue-html5-editor>.toolbar>.dashboard button:last-child{margin-right:0}.vue-html5-editor>.toolbar>.dashboard label{font-weight:bolder}.vue-html5-editor>.content{overflow:auto;padding:10px}.vue-html5-editor>.content:focus{outline:0}",undefined);

var template$9 = "<div class=\"vue-html5-editor\" :class=\"{'full-screen':fullScreen}\" :style=\"{'z-index':zIndex}\"> <div class=\"toolbar\" :style=\"{'z-index':zIndex+1}\" ref=\"toolbar\"> <ul> <template v-for=\"module in modules\"> <li :title=\"locale[module.i18n]\" @click=\"activeModule(module)\"> <span class=\"icon\" :class=\"module.icon\"></span> <template v-if=\"showModuleName === undefined ? defaultShowModuleName : showModuleName\"> &nbsp;{{locale[module.i18n]}} </template> </li> </template> </ul> <div class=\"dashboard\" v-show=\"dashboard\" ref=\"dashboard\"> <keep-alive> <div v-show=\"dashboard\" :is=\"dashboard\"></div> </keep-alive> </div> </div> <div class=\"content\" ref=\"content\" :style=\"contentStyle\" contenteditable @click=\"toggleDashboard(dashboard)\"> </div> </div>";

/**
 * Created by peak on 2017/2/9.
 */
var editor = {
    template: template$9,
    props: {
        content: {
            type: String,
            required: true,
            default: ''
        },
        height: {
            type: Number,
            default: 300,
            validator: function validator(val){
                return val >= 100
            }
        },
        zIndex: {
            type: Number,
            default: 1000
        },
        autoHeight: {
            type: Boolean,
            default: true
        },
        showModuleName: {}
    },
    data: function data(){
        return {
            // defaultShowModuleName:false
            // locale: {},
            // modules:{},
            fullScreen: false,
            dashboard: null
        }
    },
    watch: {
        content: function content(val) {
            var content = this.$refs.content.innerHTML;
            if (val !== content) {
                this.$refs.content.innerHTML = val;
            }
        },
        fullScreen: function fullScreen(val){
            var component = this;
            if (val) {
                component.parentEl = component.$el.parentNode;
                component.nextEl = component.$el.nextSibling;
                document.body.appendChild(component.$el);
                return
            }
            if (component.nextEl) {
                component.parentEl.insertBefore(component.$el, component.nextEl);
                return
            }
            component.parentEl.appendChild(component.$el);
        }
    },
    computed: {
        contentStyle: function contentStyle(){
            var style = {};
            if (this.fullScreen) {
                style.height = (window.innerHeight - this.$refs.toolbar.clientHeight - 1) + "px";
                return style
            }
            if (!this.autoHeight) {
                style.height = (this.height) + "px";
                return style
            }
            style['min-height'] = (this.height) + "px";
            return style
        }
    },
    methods: {
        toggleFullScreen: function toggleFullScreen(){
            this.fullScreen = !this.fullScreen;
        },
        enableFullScreen: function enableFullScreen(){
            this.fullScreen = true;
        },
        exitFullScreen: function exitFullScreen(){
            this.fullScreen = false;
        },
        focus: function focus(){
            this.$refs.content.focus();
        },
        toggleDashboard: function toggleDashboard(dashboard){
            this.dashboard = this.dashboard === dashboard ? null : dashboard;
        },
        execCommand: function execCommand(command, arg){
            this.restoreSelection();
            if (this.range) {
                new RangeHandler(this.range).execCommand(command, arg);
            }
            this.toggleDashboard();
            this.$emit('change', this.$refs.content.innerHTML);
        },
        getCurrentRange: function getCurrentRange(){
            return this.range
        },
        saveCurrentRange: function saveCurrentRange(){
            var this$1 = this;

            var selection = window.getSelection ? window.getSelection() : document.getSelection();
            if (!selection.rangeCount) {
                return
            }
            var content = this.$refs.content;
            for (var i = 0; i < selection.rangeCount; i++) {
                var range = selection.getRangeAt(0);
                var start = range.startContainer;
                var end = range.endContainer;
                // for IE11 : node.contains(textNode) always return false
                start = start.nodeType === Node.TEXT_NODE ? start.parentNode : start;
                end = end.nodeType === Node.TEXT_NODE ? end.parentNode : end;
                if (content.contains(start) && content.contains(end)) {
                    this$1.range = range;
                    break
                }
            }
        },
        restoreSelection: function restoreSelection(){
            var selection = window.getSelection ? window.getSelection() : document.getSelection();
            selection.removeAllRanges();
            if (this.range) {
                selection.addRange(this.range);
            } else {
                var content = this.$refs.content;
                var div = document.createElement('div');
                var range = document.createRange();
                content.appendChild(div);
                range.setStart(div, 0);
                range.setEnd(div, 0);
                selection.addRange(range);
                this.range = range;
            }
        },
        activeModule: function activeModule(module){
            if (typeof module.handler === 'function') {
                module.handler(this);
                return
            }
            if (module.hasDashboard) {
                this.toggleDashboard(("dashboard-" + (module.name)));
            }
        }
    },
    created: function created(){
        var this$1 = this;

        this.modules.forEach(function (module) {
            if (typeof module.init === 'function') {
                module.init(this$1);
            }
        });
    },
    mounted: function mounted(){
        var this$1 = this;

        var content = this.$refs.content;
        content.innerHTML = this.content;
        content.addEventListener('mouseup', this.saveCurrentRange, false);
        content.addEventListener('keyup', function () {
            this$1.$emit('change', content.innerHTML);
            this$1.saveCurrentRange();
        }, false);
        content.addEventListener('mouseout', function (e) {
            if (e.target === content) {
                this$1.saveCurrentRange();
            }
        }, false);
        this.touchHandler = function (e) {
            if (content.contains(e.target)) {
                this$1.saveCurrentRange();
            }
        };

        window.addEventListener('touchend', this.touchHandler, false);
    },
    updated: function updated(){
        // update dashboard style
        if (this.$refs.dashboard){
            this.$refs.dashboard.style.maxHeight = (this.$refs.content.clientHeight) + "px";
        }
    },
    beforeDestroy: function beforeDestroy(){
        var this$1 = this;

        window.removeEventListener('touchend', this.touchHandler);
        this.modules.forEach(function (module) {
            if (typeof module.destroyed === 'function') {
                module.destroyed(this$1);
            }
        });
    }
};

var i18nZhCn = {
    align: '对齐方式',
    image: '图片',
    list: '列表',
    link: '链接',
    unlink: '去除链接',
    table: '表格',
    font: '文字',
    'full screen': '全屏',
    text: '排版',
    eraser: '格式清除',
    info: '关于',
    color: '颜色',
    'please enter a url': '请输入地址',
    'create link': '创建链接',
    bold: '加粗',
    italic: '倾斜',
    underline: '下划线',
    'strike through': '删除线',
    subscript: '上标',
    superscript: '下标',
    heading: '标题',
    'font name': '字体',
    'font size': '文字大小',
    'left justify': '左对齐',
    'center justify': '居中',
    'right justify': '右对齐',
    'ordered list': '有序列表',
    'unordered list': '无序列表',
    'fore color': '前景色',
    'background color': '背景色',
    'row count': '行数',
    'column count': '列数',
    save: '确定',
    upload: '上传',
    progress: '进度',
    unknown: '未知',
    'please wait': '请稍等',
    error: '错误',
    abort: '中断',
    reset: '重置',
    hr: '分隔线',
    undo: '撤消',
    'line height': '行高',
    'exceed size limit': '超出大小限制'
};

var i18nEnUs = {
    align: 'align',
    image: 'image',
    list: 'list',
    link: 'link',
    unlink: 'unlink',
    table: 'table',
    font: 'font',
    'full screen': 'full screen',
    text: 'text',
    eraser: 'remove format',
    info: 'info',
    color: 'color',
    'please enter a url': 'please enter a url',
    'create link': 'create link',
    bold: 'bold',
    italic: 'italic',
    underline: 'underline',
    'strike through': 'strike through',
    subscript: 'subscript',
    superscript: 'superscript',
    heading: 'heading',
    'font name': 'font name',
    'font size': 'font size',
    'left justify': 'left justify',
    'center justify': 'center justify',
    'right justify': 'right justify',
    'ordered list': 'ordered list',
    'unordered list': 'unordered list',
    'fore color': 'fore color',
    'background color': 'background color',
    'row count': 'row count',
    'column count': 'column count',
    save: 'save',
    upload: 'upload',
    progress: 'progress',
    unknown: 'unknown',
    'please wait': 'please wait',
    error: 'error',
    abort: 'abort',
    reset: 'reset',
    hr: 'horizontal rule',
    undo: 'undo',
    'line height': 'line height',
    'exceed size limit': 'exceed size limit'
};

/**
 * Created by peak on 2017/2/24.
 */
/**
 * shadow clone
 *
 * @param source    source object
 * @param ext       extended object
 */
function mixin(source, ext) {
    if ( source === void 0 ) source = {};
    if ( ext === void 0 ) ext = {};

    Object.keys(ext).forEach(function (k) {
        // for data function
        if (k === 'data') {
            var dataSrc = source[k];
            var dataDesc = ext[k];
            if (typeof dataDesc === 'function') {
                if (typeof dataSrc !== 'function') {
                    source[k] = dataDesc;
                } else {
                    source[k] = function () { return mixin(dataSrc(), dataDesc()); };
                }
            }
        } else {
            source[k] = ext[k];
        }
    });
    return source
}

polyfill();
/**
 * Vue html5 Editor
 * @param Vue   {Vue}
 * @param options {Object}
 */
var VueHtml5Editor = function VueHtml5Editor(options) {
    if ( options === void 0 ) options = {};

    var modules = [].concat( buildInModules );
    var components = {};

    // extended modules
    if (Array.isArray(options.modules)) {
        options.modules.forEach(function (module) {
            if (module.name) {
                modules.push(module);
            }
        });
    }
    // hidden modules
    if (Array.isArray(options.hiddenModules)) {
        modules = (function () {
            var arr = [];
            modules.forEach(function (m) {
                if (!options.hiddenModules.includes(m.name)) {
                    arr.push(m);
                }
            });
            return arr
        })();
    }
    // visible modules
    if (Array.isArray(options.visibleModules)) {
        modules = (function () {
            var arr = [];
            options.visibleModules.forEach(function (name) {
                modules.forEach(function (module) {
                    if (module.name === name) {
                        arr.push(module);
                    }
                });
            });
            return arr
        })();
    }


    modules.forEach(function (module) {
        // specify the config for each module in options by name
        var config = options[module.name];
        module.config = mixin(module.config, config);

        if (module.dashboard) {
            // $options.module
            module.dashboard.module = module;
            components[("dashboard-" + (module.name))] = module.dashboard;
        }
        if (options.icons && options.icons[module.name]) {
            module.icon = options.icons[module.name];
        }

        module.hasDashboard = !!module.dashboard;
        // prevent vue sync
        module.dashboard = null;
    });

    // i18n
    var i18n = {'zh-cn': i18nZhCn, 'en-us': i18nEnUs};
    var customI18n = options.i18n || {};
    Object.keys(customI18n).forEach(function (key) {
        i18n[key] = i18n[key] ? mixin(i18n[key], customI18n[key]) : customI18n[key];
    });
    var language = options.language || 'en-us';
    var locale = i18n[language];

    // showModuleName
    var defaultShowModuleName = !!options.showModuleName;

    // ######################################
    var compo = mixin(editor, {
        data: function data() {
            return {modules: modules, locale: locale, defaultShowModuleName: defaultShowModuleName}
        },
        components: components
    });
    mixin(this, compo);
};

/**
 * global install
 *
 * @param Vue
 * @param options
 */
VueHtml5Editor.install = function install (Vue, options) {
        if ( options === void 0 ) options = {};

    Vue.component(options.name || 'vue-html5-editor', new VueHtml5Editor(options));
};

return VueHtml5Editor;

})));

/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(57)))

/***/ }),

/***/ 105:
/***/ (function(module, exports) {

/**
 * 增加前导零
 * @param {Number/String} m
 */
function formatNumber(m) {
    var n = m.toString();

    return n[1] ? n : '0' + n;
}

/**
 * 时间格式转化
 * @param {Number} date
 * @return YYYY.MM.DD hh:mm
 */
function formatTime(date) {
    // let parseDate = date instanceof Date ? date : new Date(date);
    var parseDate = new Date(date * 1000);

    var year = parseDate.getFullYear();
    var month = parseDate.getMonth() + 1;
    var day = parseDate.getDate();
    var hour = parseDate.getHours();
    var minute = parseDate.getMinutes();

    return [year, month, day].map(formatNumber).join('.') + ' ' + [hour, minute].map(formatNumber).join(':');
}

/**
 * 时间格式转化
 * @param {Number} date
 * @return YYYY.MM.DD
 */
function formatDay(date) {
    var parseDate = new Date(date * 1000);

    var year = parseDate.getFullYear();
    var month = parseDate.getMonth() + 1;
    var day = parseDate.getDate();

    return [year, month, day].map(formatNumber).join('.');
}

/**
 * 时间格式转化
 * @param {Number} date
 * @return M月D月H时
 */
function formatDayWithUnit(date) {
    var parseDate = new Date(date * 1000);
    var month = parseDate.getMonth() + 1;
    var day = parseDate.getDate();
    var hour = parseDate.getHours();

    return month + '月' + day + '日' + hour + '时';
}

/**
 * 时间格式转化
 * @param {Number} time
 * @return 当天：hh:ss；一周内：d天前；一周前：Y.M.DD
 */
function timeAgo(time) {
    var between = Date.now() / 1000 - Number(time);
    var date = new Date(Number(time) * 1000);
    var Y = date.getFullYear() + '.';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '.';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes();

    if (between < 86400) {
        //当天
        return formatNumber(h) + formatNumber(m);
    } else if (between < 604800) {
        // eslint-disable-next-line no-bitwise
        var days = ~~(between / 86400);

        return days + '天前';
    } else {
        return Y + M + formatNumber(D);
    }
}

/**
 * 保留两位小数
 * @param {Number} num
 */
function formatToFixed(num) {
    return Number(num).toFixed(2);
}

module.exports = {
    formatNumber: formatNumber,
    formatTime: formatTime,
    formatDay: formatDay,
    formatDayWithUnit: formatDayWithUnit,
    timeAgo: timeAgo,
    formatToFixed: formatToFixed
};

/***/ }),

/***/ 112:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_element_ui_src_utils_dom__ = __webpack_require__(235);



const nodeList = [];
const ctx = '@@clickoutsideContext';

let startClick;

!__WEBPACK_IMPORTED_MODULE_0_vue__["default"].prototype.$isServer && __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1_element_ui_src_utils_dom__["a" /* on */])(document, 'mousedown', e => (startClick = e));

!__WEBPACK_IMPORTED_MODULE_0_vue__["default"].prototype.$isServer && __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1_element_ui_src_utils_dom__["a" /* on */])(document, 'mouseup', e => {
  nodeList.forEach(node => node[ctx].documentHandler(e, startClick));
});
/**
 * v-clickoutside
 * @desc 点击元素外面才会触发的事件
 * @example
 * ```vue
 * <div v-element-clickoutside="handleClose">
 * ```
 */
/* harmony default export */ __webpack_exports__["a"] = ({
  bind(el, binding, vnode) {
    const id = nodeList.push(el) - 1;
    const documentHandler = function(mouseup, mousedown) {
      if (!vnode.context ||
        !mouseup.target ||
        !mousedown.target ||
        el.contains(mouseup.target) ||
        el.contains(mousedown.target) ||
        el === mouseup.target ||
        (vnode.context.popperElm &&
        (vnode.context.popperElm.contains(mouseup.target) ||
        vnode.context.popperElm.contains(mousedown.target)))) return;

      if (binding.expression &&
        el[ctx].methodName &&
        vnode.context[el[ctx].methodName]) {
        vnode.context[el[ctx].methodName]();
      } else {
        el[ctx].bindingFn && el[ctx].bindingFn();
      }
    };
    el[ctx] = {
      id,
      documentHandler,
      methodName: binding.expression,
      bindingFn: binding.value
    };
  },

  update(el, binding) {
    el[ctx].methodName = binding.expression;
    el[ctx].bindingFn = binding.value;
  },

  unbind(el) {
    let len = nodeList.length;

    for (let i = 0; i < len; i++) {
      if (nodeList[i][ctx].id === el[ctx].id) {
        nodeList.splice(i, 1);
        break;
      }
    }
  }
});


/***/ }),

/***/ 113:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/*
 * @Author: zhaoyi
 * @Date: 2017-09-13 14:11:04
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-13 14:21:42
 */
/* harmony default export */ __webpack_exports__["a"] = ({
    methods: {
        getUnreadMes: function getUnreadMes() {
            var _this = this;

            this.$post('/Notify/unread', {}, function (resp) {
                if (resp.code == 1) {
                    if (resp.data != []) {
                        _this.$store.commit('SET_UNREAD_MES', resp.data);
                    } else {
                        _this.$store.commit('SET_UNREAD_MES', {
                            0: 0,
                            1: 0,
                            2: 0,
                            3: 0
                        });
                    }
                }
            });
        }
    }
});

/***/ }),

/***/ 114:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:00:01
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-26 16:08:54
 */
/* harmony default export */ __webpack_exports__["a"] = ({
    methods: {
        getUserInfo: function getUserInfo() {
            var _this = this;

            this.$post('/UserCenter/getUserInfo', {}, function (resp) {
                if (resp.code == 1) {
                    _this.$store.commit('SET_USER_INFO', resp.data);
                } else {
                    _this.$notify.error({
                        title: '错误',
                        duration: 2000,
                        message: resp.msg || '获取用户信息失败，请稍后重试'
                    });
                }
            });
        }
    }
});

/***/ }),

/***/ 159:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:00:01
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-26 16:08:47
 */
/* harmony default export */ __webpack_exports__["a"] = ({
    methods: {
        getCompanyInfo: function getCompanyInfo() {
            var _this = this;

            this.$post('/UserCenter/getUserCompanyInfo', {}, function (resp) {
                if (resp.code == 1) {
                    if (resp.data !== null) {
                        _this.$store.commit('SET_COMPANY_INFO', resp.data);
                    }
                } else {
                    _this.$notify.error({
                        title: '错误',
                        duration: 2000,
                        message: resp.msg || '获取公司信息失败，请稍后重试'
                    });
                }
            });
        }
    }
});

/***/ }),

/***/ 160:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__ = __webpack_require__(95);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__App___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__App__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__router__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__store__ = __webpack_require__(94);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_element_ui__ = __webpack_require__(96);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_element_ui___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_element_ui__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_static_js_vue_html5_editor__ = __webpack_require__(102);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_static_js_vue_html5_editor___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_static_js_vue_html5_editor__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_vuex__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_static_css_reset_css__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_static_css_reset_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_static_css_reset_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_static_css_iconfont_css__ = __webpack_require__(98);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_static_css_iconfont_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_static_css_iconfont_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_element_ui_lib_theme_default_index_css__ = __webpack_require__(97);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_element_ui_lib_theme_default_index_css___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_10_element_ui_lib_theme_default_index_css__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__assets_js_axios__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12_static_js_filters__ = __webpack_require__(101);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__api_api__ = __webpack_require__(91);

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.













console.log(window.location.host, "http://gtapi.wxhand.com/hire", "http://api.zsdx.cn/hire", window.location.host == 'gt.wxhand.com');
console.log(window.location.host == 'gt.wxhand.com' ? "http://gtapi.wxhand.com/hire" : "http://api.zsdx.cn/hire" + '/Upload/uploadPicture');
var base = window.location.host == 'gt.wxhand.com' ? "http://gtapi.wxhand.com/hire" : "http://api.zsdx.cn/hire";
var options = {
    name: "vue-html5-editor",
    // 是否显示模块名称，开启的话会在工具栏的图标后台直接显示名称
    // if set true,will append module name to toolbar after icon
    showModuleName: false,
    // 自定义各个图标的class，默认使用的是font-awesome提供的图标
    // custom icon class of built-in modules,default using font-awesome
    icons: {},
    // 配置图片模块
    // config image module
    image: {
        // 文件最大体积，单位字节  max file size
        sizeLimit: 512 * 1024,
        // 上传参数,默认把图片转为base64而不上传
        // upload config,default null and convert image to base64
        upload: {
            url: base + '/Upload/uploadPicture',
            headers: {
                'zsdx-session-ticket': localStorage.sessionTicket,
                'zsdx-origin-name': window.location.host
            },
            params: {},
            fieldName: 'picture'
        },
        // 压缩参数,默认使用localResizeIMG进行压缩,设置为null禁止压缩
        // compression config,default resize image by localResizeIMG (https://github.com/think2011/localResizeIMG)
        // set null to disable compression
        compress: {
            width: 1600,
            height: 1600,
            quality: 80
        },
        // 响应数据处理,最终返回图片链接
        // handle response data，return image url
        uploadHandler: function uploadHandler(responseText) {
            //default accept json data like  {ok:false,msg:"unexpected"} or {ok:true,data:"image url"}
            var json = JSON.parse(responseText);

            console.log(json);
            if (json.code != 1) {
                alert(json.msg);
            } else {
                return json.data.url;
            }
        }
    },
    // 语言，内建的有英文（en-us）和中文（zh-cn）
    //default en-us, en-us and zh-cn are built-in
    language: "zh-cn",
    // 自定义语言
    i18n: {
        //specify your language here
        "zh-cn": {
            "align": "对齐方式",
            "image": "图片",
            "list": "列表",
            "link": "链接",
            "unlink": "去除链接",
            "table": "表格",
            "font": "文字",
            "full screen": "全屏",
            "text": "排版",
            "eraser": "格式清除",
            "info": "关于",
            "color": "颜色",
            "please enter a url": "请输入地址",
            "create link": "创建链接",
            "bold": "加粗",
            "italic": "倾斜",
            "underline": "下划线",
            "strike through": "删除线",
            "subscript": "上标",
            "superscript": "下标",
            "heading": "标题",
            "font name": "字体",
            "font size": "文字大小",
            "left justify": "左对齐",
            "center justify": "居中",
            "right justify": "右对齐",
            "ordered list": "有序列表",
            "unordered list": "无序列表",
            "fore color": "前景色",
            "background color": "背景色",
            "row count": "行数",
            "column count": "列数",
            "save": "确定",
            "upload": "上传",
            "progress": "进度",
            "unknown": "未知",
            "please wait": "请稍等",
            "error": "错误",
            "abort": "中断",
            "reset": "重置"
        }
    },
    // 隐藏不想要显示出来的模块
    // the modules you don't want
    hiddenModules: [],
    // 自定义要显示的模块，并控制顺序
    // keep only the modules you want and customize the order.
    // can be used with hiddenModules together
    visibleModules: ["text", "color", "font", "align", "link", "unlink", "tabulation", "image", "hr", "eraser", "undo"],
    // 扩展模块，具体可以参考examples或查看源码
    // extended modules
    modules: {
        //omit,reference to source code of build-in modules
    }
};

__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default()(__WEBPACK_IMPORTED_MODULE_12_static_js_filters__).forEach(function (key) {
    __WEBPACK_IMPORTED_MODULE_1_vue__["default"].filter(key, __WEBPACK_IMPORTED_MODULE_12_static_js_filters__[key]);
});

__WEBPACK_IMPORTED_MODULE_1_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_6_static_js_vue_html5_editor___default.a, options);
__WEBPACK_IMPORTED_MODULE_1_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_5_element_ui___default.a);
__WEBPACK_IMPORTED_MODULE_1_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_11__assets_js_axios__["a" /* default */]);
__WEBPACK_IMPORTED_MODULE_1_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_13__api_api__["a" /* default */]);

__WEBPACK_IMPORTED_MODULE_1_vue__["default"].config.productionTip = false;
/* eslint-disable no-new */
new __WEBPACK_IMPORTED_MODULE_1_vue__["default"]({
    el: '#app',
    router: __WEBPACK_IMPORTED_MODULE_3__router__["a" /* default */],
    store: __WEBPACK_IMPORTED_MODULE_4__store__["a" /* default */],
    template: '<App/>',
    components: { App: __WEBPACK_IMPORTED_MODULE_2__App___default.a }
});

/***/ }),

/***/ 161:
/***/ (function(module, exports, __webpack_require__) {

/*
* @Author: wangenbo;
* @Date: 2017-07-21 09:19:04;
* @Last Modified by: wangenbo;
* @Last Modified time: 2017-07-21 09:23:58;
*/
module.exports = [
/* 派单模块 */
{
    path: '/dispatch',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(41).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(276)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: '/',
        name: 'dispatch', //挑选广告位
        component: function component(resolve) {
            return __webpack_require__.e/* require */(3).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(274)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, {
        path: 'confirm', //确认广告
        name: 'Confirm',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(12).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(275)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
},
//一级菜单模块
{
    path: '/menu',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(38).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(295)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: 'example',
        name: 'MunuExample',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(29).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(294)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //一级菜单准备材料
    }, {
        path: 'selected',
        name: 'MenuSelected',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(11).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(296)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //已选广告位
    }]
},
// 购物车模块
{
    path: '/shop', //购物车
    name: 'ShopList',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(8).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(273)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    }
},
// 个人中心
{
    path: '/user',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(25).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(299)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{ // 钱包
        name: 'Wallet',
        path: 'wallet',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(13).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(303)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, { // 账号设置
        name: 'Account',
        path: 'account',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(22).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(298)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, { // 账号设置
        name: 'Invoice',
        path: 'invoice',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(30).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(300)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
},
// 素材库
{
    path: '/material',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(32).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(292)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: '/',
        name: 'MaterialList',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(24).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(293)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } // 素材列表
    }, {
        name: 'MaterialAdd',
        path: 'add',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(14).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(291)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } // 素材添加
    }]
},

// 收银台
{
    path: '/cashier',
    name: 'Cashier',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(15).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(302)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    }
},

// 朋友圈收银台
{
    path: '/cashier-spec',
    name: 'CashierSpec',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(23).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(301)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    }
},

/*竞标模块*/
{
    path: '/bidding',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(43).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(259)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: '/',
        name: 'BiddingSet',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(26).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(257)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //竞标设置
    }, {
        path: 'choose',
        name: 'BidChoose',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(4).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(258)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //竞标选标
    }]
},
// 消息
{
    path: '/message',
    name: 'Message',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(17).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(297)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    }
},
// 校园大使
{
    path: '/ambassador',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(37).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(262)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{ // 任务列表
        name: 'AmbassadorTaskList',
        path: 'task-list',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(31).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(264)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, { // 添加任务
        name: 'AmbassadorAddTask',
        path: 'add',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(46).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(260)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, { // 任务详情
        name: 'AmbassadorTaskDetail',
        path: 'detail',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(44).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(263)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }, { // 大使列表
        name: 'AmbassadorList',
        path: 'ambassador-list',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(45).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(261)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
},
/* 通用模块 */
{
    path: '/common',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(42).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(269)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: 'acontent',
        name: 'AdvContent',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(6).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(266)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //广告内容
    }, {
        path: 'areport',
        name: 'AdvReport',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(2).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(267)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //广告报表
    }, {
        path: 'oreport',
        name: 'orderReport',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(1).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(270)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //订单报表
    }, {
        name: 'WechatDetail',
        path: 'wechat',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(0).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(272)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } // 微信公众号详情
    }, {
        name: 'payDone',
        path: 'pay-done',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(34).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(271)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } // 付款成功提示页
    }, {
        name: 'AutoLogin', // 后台跳转，自动登陆中转页
        path: 'auto-login',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(35).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(268)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        }
    }]
},
/* 我的广告 */
{
    path: '/manage',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(39).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(288)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: 'advmanage',
        name: 'AdvManage',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(16).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(287)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //我的广告列表
    }, {
        path: 'order',
        name: 'Order',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(7).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(290)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //订单列表
    }, {
        path: 'order-detail',
        name: 'OrderDetail',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(10).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(289)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //订单详情
    }]
},
/* 朋友圈模块 */
{
    path: '/friends',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(40).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(279)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        path: 'choose',
        name: 'FriendsChoose',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(5).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(277)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //朋友圈筛选
    }, {
        path: 'example',
        name: 'FriendsExample',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(28).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(278)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //朋友圈准备材料
    }, {
        path: 'selected',
        name: 'FriendsSelected',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(9).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(280)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //朋友圈已选人群
    }]
},
/* 帮助中心模块 */
{
    path: '/help',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(27).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(282)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    children: [{
        name: 'help',
        path: '/',
        component: function component(resolve) {
            return __webpack_require__.e/* require */(33).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(281)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
        } //帮助中心列表
    }]
},
/* 登录模块 */
{
    path: '/login',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(19).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(285)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    name: 'login'
}, {
    path: '/bind',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(21).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(283)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    name: 'bind'
}, {
    path: '/forget',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(20).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(284)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    name: 'forget'
}, {
    path: '/reg',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(18).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(286)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    },
    name: 'reg'
},
// 404
{
    path: '/404',
    component: function component(resolve) {
        return __webpack_require__.e/* require */(36).then(function() { var __WEBPACK_AMD_REQUIRE_ARRAY__ = [__webpack_require__(265)]; (resolve.apply(null, __WEBPACK_AMD_REQUIRE_ARRAY__));}.bind(this)).catch(__webpack_require__.oe);
    }
}, {
    path: '*',
    redirect: '/404'
}];

/***/ }),

/***/ 162:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:11:01
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-18 17:37:58
 */



var state = {
    agentInfo: null
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["b" /* SET_AGENT_INFO */], function (state, agentInfo) {
    if (agentInfo.agent_site == null) {
        agentInfo.agent_site = {};
    }
    state.agentInfo = agentInfo;
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 163:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__types_action_types__ = __webpack_require__(7);



var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-16 13:48:53
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-15 16:22:24
 */




var state = {
    seleted: {
        hot_city: [],
        province: '',
        city: '',
        zone: '',
        channel_type: '',
        wechat_type: 0,
        read_expect: '',
        school_type: '',
        school_kind: '',
        consume_index: '',
        school_ids: '',
        tag_no: '',
        field_name: '',
        space_price: {
            min: '',
            max: ''
        },
        fans: {
            min: '',
            max: ''
        },
        sort: 0,
        order: 'tprice'
    },
    filter: {
        hot_city: '热门城市',
        province: '省份',
        city: '城市',
        zone: '地区',
        channel_type: '类型',
        wechat_type: '认证状态',
        read_expect: '阅读量',
        school_type: '学校类型',
        school_kind: '学校属性',
        consume_index: '消费能力',
        school_ids: '学校',
        tag_no: '标签',
        field_name: '广告位类型',
        space_price: '价格',
        fans: '粉丝数',
        sort: '排序方式',
        order: '排序条件'
    },
    result: [],
    hasCampusData: false,
    campusList: []
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["s" /* FILTER_SELECTED */], function (state, obj) {
    if (obj.key == 'fans' || obj.key == 'space_price') {
        state.seleted[obj.key].min = obj.value.split('-')[0];
        state.seleted[obj.key].max = obj.value.split('-')[1];
        state.seleted = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.seleted);
    } else {
        state.seleted = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.seleted, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, obj.key, obj.value));
    }

    if (state.result.indexOf(obj.key) == -1) {
        state.result.push(obj.key);
    }
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["t" /* FILTER_CLEAR */], function (state, reset) {
    state.seleted = reset;
    state.result = [];
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["u" /* FILTER_DELETE */], function (state, key) {
    for (var i = 0; i < state.result.length; i++) {
        if (state.result[i] == key) {
            state.result.splice(i, 1);
        }
    }
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["v" /* HAS_CAMPUS */], function (state, flag) {
    state.hasCampusData = flag;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["w" /* SET_CAMPUS_LIST */], function (state, list) {
    state.campusList = list;
}), _mutations);

var getters = {
    // dispatchChange: state => {
    //     return state.seleted;
    // }
};

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations,
    getters: getters
});

/***/ }),

/***/ 164:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);


var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-22 09:46:54
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-08 16:00:21
 */



var state = {
    getlist: false,
    getShopListFlag: false
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["i" /* GET_SHOP_PREVIEW_LIST */], function (state, flag) {
    state.getlist = flag;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["j" /* GET_SHOP_LIST */], function (state, flag) {
    state.getShopListFlag = flag;
}), _mutations);

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 165:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:11:01
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-12 11:42:33
 */



var state = {
    companyInfo: {
        "hire_id": "",
        "name": "",
        "category_id": '',
        "province": '',
        "city": '',
        "area": '',
        "address": "",
        "link": '',
        "logo": "",
        "mobile": "",
        "qq": "",
        "site": "",
        "status": ''
    }
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["d" /* SET_COMPANY_INFO */], function (state, companyInfo) {
    state.companyInfo = companyInfo;
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 166:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__types_action_types__ = __webpack_require__(7);



var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-22 09:46:54
 * @Last Modified by: ice
 * @Last Modified time: 2017-11-06 16:32:52
 */



var state = {
    content: {
        "wechat_article_top": {
            "need_preview": 1, //是否需要预览
            "title": "", //标题
            "show_cover_pic": "", //是否在正文顶部插入封面原图片 by ice
            "cover_url": "", //封面
            "desc": "", //摘要
            "author": "", //作者
            "content": "", //正文
            "source_url": "", //原文链接
            "remark": "" //备注
        },
        "wechat_menu_first": {
            "title": "", //菜单名称
            "jump_url": "", //跳转链接
            "remark": "" //备注
        }
    }
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["g" /* ADVERTISE_CONTENT */], function (state, obj) {
    state.content = obj;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["h" /* SET_ADVERTISE_CONTENT */], function (state, obj) {
    state.content.wechat_article_top = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.content.wechat_article_top, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, obj.name, obj.value));
}), _mutations);

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 167:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__types_action_types__ = __webpack_require__(7);



var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-16 13:48:53
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-10-31 17:13:47
 */




var state = {
    seleted: {
        hot_city: [],
        province: '',
        city: '',
        zone: '',
        channel_type: '',
        wechat_type: 0,
        read_expect: '',
        school_type: '',
        school_kind: '',
        consume_index: '',
        school_ids: '',
        tag_no: '',
        field_name: '',
        space_price: {
            min: '',
            max: ''
        },
        fans: {
            min: '',
            max: ''
        },
        sort: '',
        order: ''
    },
    filter: {
        hot_city: '热门城市',
        province: '省份',
        city: '城市',
        zone: '地区',
        channel_type: '综合号',
        wechat_type: '公众号类型',
        read_expect: '平均阅读量',
        school_type: '学校类型',
        school_kind: '学校属性',
        consume_index: '消费能力',
        school_ids: '学校',
        tag_no: '标签',
        field_name: '广告位类型',
        space_price: '价格',
        fans: '粉丝数',
        sort: '排序方式',
        order: '排序条件'
    },
    result: [],
    hasCampusData: false,
    campusList: [],
    getChannelListFlag: false
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["s" /* FILTER_SELECTED */], function (state, obj) {
    if (obj.key == 'fans' || obj.key == 'space_price') {
        state.seleted[obj.key].min = obj.value.split('-')[0];
        state.seleted[obj.key].max = obj.value.split('-')[1];
        state.seleted = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.seleted);
    } else {
        state.seleted = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.seleted, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, obj.key, obj.value));
    }

    if (state.result.indexOf(obj.key) == -1) {
        state.result.push(obj.key);
    }
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["t" /* FILTER_CLEAR */], function (state, reset) {
    state.seleted = reset;
    state.result = [];
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["u" /* FILTER_DELETE */], function (state, key) {
    for (var i = 0; i < state.result.length; i++) {
        if (state.result[i] == key) {
            state.result.splice(i, 1);
        }
    }
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["v" /* HAS_CAMPUS */], function (state, flag) {
    state.hasCampusData = flag;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["w" /* SET_CAMPUS_LIST */], function (state, list) {
    state.campusList = list;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["A" /* GET_CHANNEL_LIST */], function (state, flag) {
    state.getChannelListFlag = flag;
}), _mutations);

var getters = {
    dispatchChange: function dispatchChange(state) {
        return state.seleted;
    }
};

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations,
    getters: getters
});

/***/ }),

/***/ 168:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__types_action_types__ = __webpack_require__(7);



var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-28 15:33:19
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-06 10:53:35
 */



var state = {
    selected: {
        method: {
            name: 'method',
            value: '1',
            label: '竞价'
        }
    },
    delItem: '',
    refreshFriendsList: false
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["k" /* FILTER_FRIENDS */], function (state, obj) {
    state.selected[obj.name] = obj;
    state.selected = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, state.selected);
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["l" /* FILTER_CLEAR_FRIEND */], function (state, obj) {
    state.selected = obj;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["m" /* DEL_FRIEND_RESULT */], function (state, str) {
    state.delItem = str;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_2__types_mutation_types__["n" /* REFRESH_FRIENDS_LIST */], function (state, flag) {
    state.refreshFriendsList = flag;
}), _mutations);

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 169:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:11:01
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-16 17:59:49
 */



var state = {
    isLogin: false
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["a" /* SET_IS_LOGIN */], function (state, isLogin) {
    state.isLogin = isLogin;
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 170:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);


var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-24 09:10:52
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-12-05 13:52:52
 */




var state = {
    space_field_name: {
        wechat_article_top: '头条',
        wechat_article_piece: '头条贴片',
        wechat_menu_first: '一级菜单',
        wechat_menu_second: '二级菜单',
        tieba_banner: '贴吧Banner',
        tieba_post_top: '置顶帖',
        weibo_send_direct: '微博直发',
        weibo_send_forward: '微博转发',
        forum_banner: '论坛banner',
        forum_post_top: '论坛置顶帖',
        qq_zone: '空间说说',
        weixin_timeline: '朋友圈',
        weixin_group_send_direct: '微信群直发',
        qq_group_send_direct: 'QQ群直发'
    },
    channel_cate_name: {
        wechat: '微信公众号',
        weibo: '微博',
        tieba: '百度贴吧',
        forum: '论坛',
        qq: 'QQ',
        qq_group: 'QQ群',
        weixin: '微信',
        weixin_group: '微信群'
    },
    school_kind: {
        1: '综合',
        2: '理工',
        3: '师范',
        4: '财经',
        5: '医科',
        6: '农林',
        7: '政法',
        8: '民族',
        9: '语言',
        10: '艺术',
        11: '体育',
        12: '军事'
    },
    hot_city: [{
        id: '1',
        name: '北京'
    }, {
        id: '2',
        name: '上海'
    }, {
        id: '202',
        name: '广州'
    }, {
        id: '203',
        name: '深圳'
    }, {
        id: '88',
        name: '杭州'
    }, {
        id: '75',
        name: '南京'
    }, {
        id: '171',
        name: '武汉'
    }, {
        id: '4',
        name: '重庆'
    }, {
        id: '188',
        name: '长沙'
    }, {
        id: '258',
        name: '成都'
    }, {
        id: '125',
        name: '南昌'
    }],
    center_city: [{
        id: '',
        name: '不限'
    }, {
        id: '1',
        name: '北京'
    }, {
        id: '73',
        name: '上海'
    }],
    important_city: [{
        id: '',
        name: '不限'
    }, {
        id: '168',
        name: '武汉'
    }, {
        id: '288',
        name: '西安'
    }, {
        id: '181',
        name: '长沙'
    }, {
        id: '151',
        name: '郑州'
    }, {
        id: '78',
        name: '苏州'
    }, {
        id: '2',
        name: '天津'
    }, {
        id: '37',
        name: '沈阳'
    }, {
        id: '197',
        name: '深圳'
    }, {
        id: '135',
        name: '青岛'
    }, {
        id: '115',
        name: '厦门'
    }, {
        id: '134',
        name: '济南'
    }, {
        id: '74',
        name: '南京'
    }, {
        id: '88',
        name: '宁波'
    }, {
        id: '195',
        name: '广州'
    }, {
        id: '60',
        name: '哈尔滨'
    }, {
        id: '87',
        name: '杭州'
    }, {
        id: '38',
        name: '大连'
    }, {
        id: '114',
        name: '福州'
    }, {
        id: '235',
        name: '成都'
    }, {
        id: '234',
        name: '重庆'
    }],
    other_filter: [{
        key: 'read_expect',
        label: '平均阅读量',
        list: [{
            name: '不限',
            value: ''
        }, {
            name: '200-500',
            value: '200-500'
        }, {
            name: '500-1000',
            value: '500-1000'
        }, {
            name: '1000-2000',
            value: '1000-2000'
        }, {
            name: '2000-4000',
            value: '2000-4000'
        }, {
            name: '4000以上',
            value: '4000-999999'
        }]
    }, {
        key: 'channel_type',
        label: '综合号',
        list: [{
            value: '',
            name: '不限'
        }, {
            name: '是',
            value: '2'
        }, {
            name: '否',
            value: '1'
        }]
    }, {
        key: 'wechat_type',
        label: '公众号类型',
        list: [{
            name: '不限',
            value: ''
        }, {
            name: '订阅号',
            value: '1'
        }, {
            name: '服务号',
            value: '2'
        }, {
            name: '认证订阅号',
            value: '3'
        }, {
            name: '认证服务号',
            value: '4'
        }]
    }, {
        key: 'zone',
        label: '所属地区',
        list: [{
            value: '',
            name: '不限'
        }, {
            name: '华东',
            value: '华东'
        }, {
            name: '华南',
            value: '华南'
        }, {
            name: '华中',
            value: '华中'
        }, {
            name: '华北',
            value: '华北'
        }, {
            name: '西北',
            value: '西北'
        }, {
            name: '西南',
            value: '西南'
        }, {
            name: '东北',
            value: '东北'
        }, {
            name: '台港澳',
            value: '台港澳'
        }]
    }, {
        key: 'school_kind',
        label: '学校属性',
        list: [{
            name: '不限',
            value: ''
        }, {
            name: '综合类',
            value: '1'
        }, {
            name: '理工类',
            value: '2'
        }, {
            name: '师范类',
            value: '3'
        }, {
            name: '财经类',
            value: '4'
        }, {
            name: '医科类',
            value: '5'
        }, {
            name: '农林类',
            value: '6'
        }, {
            name: '政法类',
            value: '7'
        }, {
            name: '民族类',
            value: '8'
        }, {
            name: '语言类',
            value: '9'
        }, {
            name: '艺术类',
            value: '10'
        }, {
            name: '体育类',
            value: '11'
        }, {
            name: '军事类',
            value: '12'
        }]
    }, {
        key: 'school_type',
        label: '学校类型',
        list: [{
            name: '不限',
            value: ''
        }, {
            name: '985/211',
            value: '1'
        }, {
            name: '211',
            value: '2'
        }, {
            name: '本科',
            value: '3'
        }, {
            name: '专科',
            value: '4'
        }]
    }, {
        key: 'consume_index',
        label: '消费能力',
        list: [{
            name: '不限',
            value: ''
        }, {
            name: '20000以上',
            value: '20000-9999999'
        }, {
            name: '10000-20000',
            value: '10000-20000'
        }, {
            name: '10000以下',
            value: '0-10000'
        }]
    }, {
        key: 'tag_no',
        label: '标签',
        list: []
    }],
    price_list: [{
        id: '-',
        name: '不限'
    }, {
        id: '0-200',
        name: '200以下'
    }, {
        id: '200-500',
        name: '200-500'
    }, {
        id: '500-1000',
        name: '500-1000'
    }, {
        id: '1000-2000',
        name: '1000-2000'
    }, {
        id: '2000-3000',
        name: '2000-3000'
    }, {
        id: '3000-99999',
        name: '3000以上'
    }],
    fans_list: [{
        id: '-',
        name: '不限'
    }, {
        id: '0-5000',
        name: '5000以下'
    }, {
        id: '5000-10000',
        name: '5000-1万'
    }, {
        id: '10000-20000',
        name: '1万-2万'
    }, {
        id: '20000-30000',
        name: '2万-3万'
    }, {
        id: '30000-99999',
        name: '3万以上'
    }],
    method_list: [{
        id: 1,
        name: '竞价',
        label: '1',
        icon: 'icon-tupian',
        desc: '文字＋1张图片',
        tool: '3千元起投'
    }, {
        id: 2,
        name: '排期',
        label: '2',
        icon: 'icon-images',
        desc: '文字＋多张图片',
        tool: '5万元起投'
    }, {
        id: 3,
        name: '排期',
        label: '3',
        icon: 'icon-shipin',
        desc: '6秒视频',
        tool: '20万元起投'
    }],
    school_level: {
        0: '未知',
        1: '本科',
        2: '专科',
        3: '承认教育',
        4: '中专',
        5: '培训学校'
    }
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["x" /* MODELS_BASE */], function (state, obj) {}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["y" /* CHANGE_BASE_CITY */], function (state, obj) {
    state.center_city = obj.center;
    state.important_city = obj.important;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["z" /* SET_TAG_LISTS */], function (state, list) {
    for (var i = 0; i < state.other_filter.length; i++) {
        if (state.other_filter[i].key == 'tag_no') {
            state.other_filter[i].list = list;
        }
    }
}), _mutations);

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 171:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);


var _mutations;

/*
 * @Author: wangenbo
 * @Date: 2017-08-28 15:33:19
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-13 10:08:26
 */



var state = {
    orderSelected: [],
    orderTotalPrice: 0,
    orderList: [],
    needRefresh: false
};

var mutations = (_mutations = {}, __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["o" /* ORDER_OPERATE */], function (state, obj) {
    state.orderSelected = obj.selected;
    state.orderTotalPrice = obj.price;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["p" /* ORDER_LIST */], function (state, obj) {
    state.orderList = obj;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["q" /* REFRESH_ORDER_LIST */], function (state, flag) {
    state.needRefresh = flag;
}), __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()(_mutations, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["r" /* CLEAR_SELECTED_LIST */], function (state, flag) {
    if (flag) {
        state.orderSelected = [];
    }
}), _mutations);

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 172:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-08 17:22:48
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-08 17:27:21
 */



var state = {
    pay: {}
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["f" /* PAY_DONE_INFO */], function (state, obj) {
    state.pay[obj.key] = {
        type: obj.type,
        arg: obj.arg
    };
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 173:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:11:01
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-13 14:15:22
 */



var state = {
    unread: {}
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["c" /* SET_UNREAD_MES */], function (state, unread) {
    state.unread = unread;
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 174:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__ = __webpack_require__(4);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__types_action_types__ = __webpack_require__(7);

/*
 * @Author: zhaoyi
 * @Date: 2017-09-11 11:11:01
 * @Last Modified by:   redman9
 * @Last Modified time: 2017-09-29 14:21:41
 */



var state = {
    userInfo: {
        'auth_status': 0,
        'money': '',
        'phone': '',
        'status': 0
    }
};

var mutations = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_defineProperty___default()({}, __WEBPACK_IMPORTED_MODULE_1__types_mutation_types__["e" /* SET_USER_INFO */], function (state, userInfo) {
    state.userInfo = userInfo;
});

/* harmony default export */ __webpack_exports__["a"] = ({
    state: state,
    mutations: mutations
});

/***/ }),

/***/ 175:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_header_header__ = __webpack_require__(247);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_header_header___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__components_header_header__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__components_common_customer_service__ = __webpack_require__(244);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__components_common_customer_service___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3__components_common_customer_service__);

//
//
//
//
//
//
//
//
//
//





/* harmony default export */ __webpack_exports__["default"] = ({
    components: {
        navHeader: __WEBPACK_IMPORTED_MODULE_2__components_header_header___default.a,
        customerService: __WEBPACK_IMPORTED_MODULE_3__components_common_customer_service___default.a
    },
    name: 'app',
    data: function data() {
        return {
            showHeader: false
        };
    },

    computed: __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default()({}, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* mapState */])({
        isLogin: function isLogin(state) {
            return state.isLogin.isLogin;
        }
        // companyInfo: state => state.companyInfo.companyInfo
    })),
    watch: {
        $route: function $route() {
            // 谷歌统计代码
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            function fnCount() {
                gtag('js', new Date());
                gtag('config', 'UA-111208696-1');
            }
            fnCount();
            if (!(this.$route.name == 'login' || this.$route.name == 'reg' || this.$route.name == 'forget' || this.$route.name == 'bind' || this.$route.name == 'help')) {
                this.showHeader = true;
            } else {
                this.showHeader = false;
            }
        },
        isLogin: function isLogin(val) {
            if (val) {
                this.getAgentInfo();
            }
        }
    },
    methods: {
        /**
         * 获取代理信息
         */
        getAgentInfo: function getAgentInfo() {
            var _this = this;

            this.$post('/Index/getAgentInfo', {}, function (resp) {
                if (resp.code == 1) {
                    var keywords = document.querySelector("meta[name=keywords]");
                    var description = document.querySelector("meta[name=description]");

                    if (resp.data.agent_site) {
                        document.title = resp.data.agent_site.site_title;
                        keywords.setAttribute('content', resp.data.agent_site.site_keyword);
                        description.setAttribute('content', resp.data.agent_site.site_description);
                    }

                    _this.$store.commit('SET_AGENT_INFO', resp.data);
                } else {
                    _this.$message.error(resp.msg || '获取代理信息失败，请刷新重试或联系客服');
                }
            });
        }
    }
});

/***/ }),

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_static_js_clickoutside__ = __webpack_require__(112);

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//




/* harmony default export */ __webpack_exports__["default"] = ({
    directives: { Clickoutside: __WEBPACK_IMPORTED_MODULE_2_static_js_clickoutside__["a" /* default */] },
    data: function data() {
        return {
            flag: false,
            serviceType: 0,
            phoneDialog: false,
            dialogWechat: false,
            showQrImage: false,
            qrUrl: '',
            hideFlag: true
        };
    },

    computed: __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default()({}, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* mapState */])({
        agentInfo: function agentInfo(state) {
            return state.agentInfo.agentInfo;
        }
    })),
    watch: {
        agentInfo: function agentInfo(val) {
            if (val && val.agent_site.service_type) {
                this.serviceType = val.agent_site.service_type;
                if (val.agent_site.service_type == 1) {
                    (function (m, ei, q, i, a) {
                        var j = void 0,
                            s = void 0;

                        m[i] = m[i] || function () {
                            (m[i].a = m[i].a || []).push(arguments);
                        };
                        j = ei.createElement(q);
                        s = ei.getElementsByTagName(q)[0];
                        j.async = true;
                        j.charset = 'UTF-8';
                        j.src = '//static.meiqia.com/dist/meiqia.js?_=t';
                        s.parentNode.insertBefore(j, s);
                    })(window, document, 'script', '_MEIQIA');
                    _MEIQIA('entId', 62232);
                    _MEIQIA('withoutBtn');
                }
            }
        }
    },
    methods: {
        open: function open() {
            this.flag = true;
        },
        hide: function hide() {
            this.flag = false;
        },
        showPhone: function showPhone() {
            this.$alert('若需加急或者其他服务请联系：' + this.agentInfo.agent_site.service_phone, '联系客服', {
                confirmButtonText: '确定',
                closeOnClickModal: true
            });
            this.flag = false;
        },
        showService: function showService() {
            if (this.serviceType == 1) {
                this.meiqiaToggle();
            } else if (this.serviceType == 2) {
                this.$alert('QQ：' + this.agentInfo.agent_site.service_extra, '联系方式', {
                    confirmButtonText: '确定',
                    closeOnClickModal: true
                });
            } else if (this.serviceType == 3) {
                this.dialogWechat = true;
            }
        },
        meiqiaToggle: function meiqiaToggle() {
            if (document.getElementById("MEIQIA-PANEL-HOLDER").style.visibility == 'visible') {
                _MEIQIA('hidePanel');
            } else {
                _MEIQIA('showPanel');
                this.flag = false;
            }
        },
        fnGetQrUrl: function fnGetQrUrl(callback) {
            var _this = this;

            if (true) {
                _this.$post('/UserCenter/bindQrcode', {}, function (json) {
                    if (json.code == 1) {
                        _this.qrUrl = json.data.qr_code;
                        callback(json.data.expire_in);
                    } else {
                        _this.$notify({
                            title: '警告',
                            message: json.msg,
                            duration: 2000,
                            type: 'warning'
                        });
                    }
                });
            }
        },
        fnCountDown: function fnCountDown(time) {
            var second = time;
            var _this = this;

            var timer = setInterval(function () {
                second--;
                if (second <= 0) {
                    clearInterval(timer);
                    _this.fnGetQrUrl(function (time) {
                        _this.fnCountDown(time);
                    });
                }
            }, 1000);
        }
    },
    created: function created() {
        var _this = this;

        setTimeout(function () {
            if (_this.$route.name === 'help' || _this.$route.name === 'reg' || _this.$route.name == undefined || _this.$route.name === 'forget') {
                _this.hideFlag = false;
            } else {
                _this.hideFlag = true;
                _this.fnGetQrUrl(function (time) {
                    _this.fnCountDown(time);
                });
            }
        }, 1000);
    }
});

/***/ }),

/***/ 177:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    props: ['show'],
    data: function data() {
        return {
            step: 1
        };
    },

    computed: {
        visble: {
            get: function get() {
                return this.show;
            },
            set: function set(v) {
                this.$emit('update:show', v);
            }
        }
    },
    watch: {
        visble: function visble(val) {
            if (val) {
                this.addClass('html', 'lock-scroll');
                this.addClass('body', 'lock-scroll');
            } else {
                this.removeClass('html', 'lock-scroll');
                this.removeClass('body', 'lock-scroll');
            }
        }
    },
    methods: {
        addClass: function addClass(el, className) {
            if (document.querySelector(el).className.indexOf(className) < 0) {
                document.querySelector(el).className += className;
            }
        },
        removeClass: function removeClass(el, className) {
            if (document.querySelector(el).className.indexOf(className) > -1) {
                document.querySelector(el).className = document.querySelector(el).className.replace(className, '');
            }
        }
    }
});

/***/ }),

/***/ 178:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__ = __webpack_require__(18);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vuex__ = __webpack_require__(15);


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ __webpack_exports__["default"] = ({
    props: {
        showLogo: {
            type: Boolean,
            default: false
        },
        startAnimate: {
            type: Boolean,
            default: true
        },
        unread: {
            default: []
        },
        isLogin: {
            disfalut: false,
            type: Boolean
        }
    },
    data: function data() {
        return {
            // 购物车预览列表
            shopPreviewList: [],
            // 失效的广告位数量
            invalidCount: 0,
            totalCount: 0,
            menuList: {
                menu_link_wechat: 'dispatch',
                menu_link_moments: 'FriendsChoose',
                menu_link_bid: 'BiddingSet'
            },
            disabledList: {},
            authToken: ''
        };
    },

    methods: {
        /**
         * 跳转到首页
         */
        jumpToHome: function jumpToHome() {
            this.$router.push({
                name: 'dispatch'
            });
        },
        fnGoShop: function fnGoShop() {
            if (this.shopPreviewList.length != 0) {
                this.$router.push({ name: 'ShopList' });
            }
        },
        ctrlActiveLineWithDropdown: function ctrlActiveLineWithDropdown(flag, selector) {
            if (flag) {
                document.querySelector(selector + ' .active-line i').style.width = '100%';
            } else {
                document.querySelector(selector + ' .active-line i').style.width = 0;
            }
        },

        /**
        * 删除购物车预览中的渠道
        *
        * @param {String} no: 渠道编号
        * @return {Boolean}
        */
        fnDelShopListItem: function fnDelShopListItem(event, no) {
            var _this = this;

            _this.disabledList[no] = true;
            _this.disabledList = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, _this.disabledList);
            _this.$post('/AdvertiseSelect/dealWithSpace', { space_nos: no, status: -1 }, function (json) {
                if (json.code == 1) {
                    // _this.fnGetShopListPreview();
                    for (var i = 0; i < _this.shopPreviewList.length; i++) {
                        if (no == _this.shopPreviewList[i].space_no) {
                            _this.shopPreviewList.splice(i, 1);
                            _this.totalCount--;
                        }
                    }
                    _this.$store.commit('GET_CHANNEL_LIST', true);
                    _this.$store.commit('GET_SHOP_LIST', true);
                } else {
                    _this.$notify({
                        title: '警告',
                        message: json.msg,
                        duration: 2000,
                        type: 'warning'
                    });
                }
                _this.disabledList[no] = false;
                _this.disabledList = __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_object_assign___default()({}, _this.disabledList);
            });
        },
        fnGoShopCar: function fnGoShopCar() {
            this.$router.push({ name: 'ShopList' });
        },
        fnGetShopListPreview: function fnGetShopListPreview() {
            var _this = this;

            _this.$post('/AdvertiseSelect/previewList', {}, function (json) {
                if (json.code == 1) {
                    _this.shopPreviewList = json.data.list;
                    _this.invalidCount = json.data.invalid_count;
                    _this.totalCount = json.data.count;
                    _this.getList = false;
                } else {
                    _this.$notify({
                        title: '警告',
                        message: json.msg,
                        duration: 2000,
                        type: 'warning'
                    });
                }
            });
        },
        fnGoAdvManage: function fnGoAdvManage() {
            if (this.$route.name === 'AdvManage') {
                location.reload();
            } else {
                this.$router.push({ name: 'AdvManage' });
            }
        },


        /**
         * 退出登陆
         */
        logout: function logout() {
            var _this = this;

            this.$post('/User/loginOut', {}, function (resp) {
                if (resp.code == 1) {
                    _this.$store.commit('SET_IS_LOGIN', false);
                    _this.clearSessionTicket();
                    _this.$router.push({
                        name: 'login'
                    });
                }
            });
        },
        jumpout: function jumpout(type) {
            if (type == 'login') {
                this.$router.push({
                    name: 'login'
                });
            } else if (type == 'reg') {
                this.$router.push({
                    name: 'reg'
                });
            }
        },
        getAuthToken: function getAuthToken(success, fail) {
            this.$post('/Oauth/getToken', {}, function (resp) {
                if (resp.code == 1) {
                    if (success) {
                        success(resp.data);
                    }
                } else {
                    if (fail) {
                        fail();
                    }
                }
            });
        },
        openLink: function openLink(str) {
            if (str == 'menu_link_amb') {
                var host = location.host;
                var ambWindow = window.open();

                this.getAuthToken(function (token) {
                    if (host.indexOf('zsdx.cn') >= 0) {
                        ambWindow.location.href = 'http://amb.zsdx.cn/project/list?oauth_token=' + token;
                    } else {
                        ambWindow.location.href = 'http://amb-dev.wxhand.com/project/list?oauth_token=' + token;
                    }
                }, function () {
                    ambWindow.close();
                });
            } else {
                if (/^https?:\/\//.test(str)) {
                    window.open(str);
                } else {
                    window.open('http://' + str);
                }
            }
        }
    },
    computed: __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default()({}, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_2_vuex__["a" /* mapState */])({
        agentInfo: function agentInfo(state) {
            return state.agentInfo.agentInfo;
        },
        userInfo: function userInfo(state) {
            return state.userInfo.userInfo;
        }
    }), {
        totalPrice: function totalPrice() {
            var total = 0;
            var tax = 0;

            for (var i = 0; i < this.shopPreviewList.length; i++) {
                total += Number(this.shopPreviewList[i].space_price);
                tax += Number(this.shopPreviewList[i].space_price) * 0.06;
            }

            return Number(total + tax).toFixed(2);
        },

        getList: {
            get: function get() {
                return this.$store.state.car.getlist;
            },
            set: function set(val) {
                this.$store.commit('GET_SHOP_PREVIEW_LIST', val);
            }
        },
        companyInfo: function companyInfo() {
            return this.$store.state.companyInfo.companyInfo;
        },
        hasNewMessage: function hasNewMessage() {
            for (var i in this.unread) {
                if (this.unread[i] > 0) {
                    return true;
                }
            }
            return false;
        },
        carPayTax: function carPayTax() {
            var total = 0;

            for (var i = 0; i < this.shopPreviewList.length; i++) {
                total += Number(this.shopPreviewList[i].space_price) * 0.06;
            }

            return total.toFixed(2);
        }
    }),
    watch: {
        isLogin: function isLogin(val) {
            if (val) {
                this.fnGetShopListPreview();
            }
        },
        getList: function getList(val) {
            if (val) {
                this.fnGetShopListPreview();
            }
        }
    }
});

/***/ }),

/***/ 179:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__ = __webpack_require__(28);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__header_nav__ = __webpack_require__(246);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__header_nav___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__header_nav__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__assets_mixin_get_user_info__ = __webpack_require__(114);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__assets_mixin_get_company_info__ = __webpack_require__(159);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__assets_mixin_get_unread_mes__ = __webpack_require__(113);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__common_new_user_alert__ = __webpack_require__(245);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__common_new_user_alert___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6__common_new_user_alert__);

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//








/* harmony default export */ __webpack_exports__["default"] = ({
    components: {
        headerNav: __WEBPACK_IMPORTED_MODULE_2__header_nav___default.a,
        newUserAlert: __WEBPACK_IMPORTED_MODULE_6__common_new_user_alert___default.a
    },
    mixins: [__WEBPACK_IMPORTED_MODULE_4__assets_mixin_get_company_info__["a" /* default */], __WEBPACK_IMPORTED_MODULE_5__assets_mixin_get_unread_mes__["a" /* default */], __WEBPACK_IMPORTED_MODULE_3__assets_mixin_get_user_info__["a" /* default */]],
    data: function data() {
        return {
            fixedFlag: false, // 是否展示fixed定位的导航条
            scrollX: 0, // 水平滚动距离，用于fixed定位的导航条left值
            checkMesTimer: null,
            isLogin: false,
            newUserAlertFlag: false
        };
    },

    computed: __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default()({}, __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1_vuex__["a" /* mapState */])({
        unRead: function unRead(state) {
            return state.unreadMes.unread;
        },
        agentInfo: function agentInfo(state) {
            return state.agentInfo.agentInfo;
        }
    })),
    created: function created() {
        var _this = this;

        this.$nextTick(function () {
            _this.checkLogin();
        });
    },
    mounted: function mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    methods: {
        /**
         * 垂直滚动时，判断导航条是否fixed定位
         */
        handleScroll: function handleScroll(e) {
            this.scrollX = -window.scrollX;
            if (this.fixedFlag && window.scrollY <= 81) {
                this.fixedFlag = false;
            } else if (!this.fixedFlag && window.scrollY > 81) {
                this.fixedFlag = true;
            }
        },


        /**
         * 检查是否登陆
         */
        checkLogin: function checkLogin() {
            var _this = this;

            this.$post('/User/checkLogin', {}, function (resp) {
                if (resp.code == 1) {
                    if (resp.data.is_login) {
                        _this.isLogin = true;
                        _this.$store.commit('SET_IS_LOGIN', true);
                        _this.checkNewUser();
                        _this.getUserInfo();
                        _this.getCompanyInfo();
                        _this.getUnreadMes();
                        _this.checkMesTimer = setInterval(function () {
                            _this.getUnreadMes();
                        }, 60000);
                    }
                }
            });
        },


        /**
         *  检查是否是新用户
         */
        checkNewUser: function checkNewUser() {
            var _this2 = this;

            this.$post('/UserCenter/checkNewUserAlert', {}, function (resp) {
                if (resp.code == 1 && resp.data.need_alert == 1) {
                    _this2.newUserAlertFlag = true;
                }
            });
        },


        /**
         * 跳转到首页
         */
        jumpToHome: function jumpToHome() {
            this.$router.push({
                name: 'dispatch'
            });
        }
    },
    beforeDestroy: function beforeDestroy() {
        if (this.checkMesTimer) {
            clearInterval(this.checkMesTimer);
        }
    }
});

/***/ }),

/***/ 236:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 237:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 238:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 239:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 240:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 241:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 244:
/***/ (function(module, exports, __webpack_require__) {

function injectStyle (ssrContext) {
  __webpack_require__(239)
  __webpack_require__(238)
}
var Component = __webpack_require__(24)(
  /* script */
  __webpack_require__(176),
  /* template */
  __webpack_require__(250),
  /* styles */
  injectStyle,
  /* scopeId */
  "data-v-2b57fb59",
  /* moduleIdentifier (server only) */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 245:
/***/ (function(module, exports, __webpack_require__) {

function injectStyle (ssrContext) {
  __webpack_require__(241)
}
var Component = __webpack_require__(24)(
  /* script */
  __webpack_require__(177),
  /* template */
  __webpack_require__(252),
  /* styles */
  injectStyle,
  /* scopeId */
  "data-v-8503060c",
  /* moduleIdentifier (server only) */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 246:
/***/ (function(module, exports, __webpack_require__) {

function injectStyle (ssrContext) {
  __webpack_require__(237)
}
var Component = __webpack_require__(24)(
  /* script */
  __webpack_require__(178),
  /* template */
  __webpack_require__(249),
  /* styles */
  injectStyle,
  /* scopeId */
  "data-v-290f3254",
  /* moduleIdentifier (server only) */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 247:
/***/ (function(module, exports, __webpack_require__) {

function injectStyle (ssrContext) {
  __webpack_require__(236)
}
var Component = __webpack_require__(24)(
  /* script */
  __webpack_require__(179),
  /* template */
  __webpack_require__(248),
  /* styles */
  injectStyle,
  /* scopeId */
  "data-v-21c6527e",
  /* moduleIdentifier (server only) */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 248:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "header-container"
  }, [_c('div', {
    staticClass: "centered"
  }, [_c('div', [_c('h1', [(_vm.agentInfo && _vm.agentInfo.agent_site.site_logo) ? [_c('img', {
    staticClass: "agent-logo",
    attrs: {
      "src": _vm.agentInfo.agent_site.site_logo
    },
    on: {
      "click": _vm.jumpToHome
    }
  })] : [_c('img', {
    staticClass: "agent-logo",
    attrs: {
      "src": "http://cdn.wxhand.com/wei/images/hire/common/logo.png"
    },
    on: {
      "click": _vm.jumpToHome
    }
  })]], 2), _vm._v(" "), _c('header-nav', {
    attrs: {
      "start-animate": _vm.fixedFlag,
      "unread": _vm.unRead,
      "is-login": _vm.isLogin
    }
  })], 1)]), _vm._v(" "), _c('div', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.fixedFlag),
      expression: "fixedFlag"
    }],
    staticClass: "fixed-container",
    style: ({
      'left': _vm.scrollX + 'px'
    })
  }, [_c('div', {
    staticClass: "centered"
  }, [_c('header-nav', {
    attrs: {
      "show-logo": true,
      "start-animate": _vm.fixedFlag,
      "unread": _vm.unRead,
      "is-login": _vm.isLogin
    }
  })], 1)]), _vm._v(" "), _c('new-user-alert', {
    attrs: {
      "show": _vm.newUserAlertFlag
    },
    on: {
      "update:show": function($event) {
        _vm.newUserAlertFlag = $event
      }
    }
  })], 1)
},staticRenderFns: []}

/***/ }),

/***/ 249:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "header-nav-wrapper clearfix"
  }, [_c('transition', {
    attrs: {
      "name": "el-fade-in"
    }
  }, [_c('a', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.showLogo && _vm.startAnimate),
      expression: "showLogo && startAnimate"
    }],
    staticClass: "iconfont icon-logo",
    attrs: {
      "href": "javascript:;"
    },
    on: {
      "click": _vm.jumpToHome
    }
  })]), _vm._v(" "), (_vm.agentInfo) ? _c('ul', {
    staticClass: "nav-wrapper",
    class: {
      'move-right': _vm.startAnimate, 'move-left': !_vm.startAnimate
    }
  }, _vm._l((_vm.agentInfo.agent_site_menu), function(item, index) {
    return _c('li', {
      key: index
    }, [(item.menu_type == 1) ? [_c('router-link', {
      staticClass: "nav-item",
      attrs: {
        "to": {
          name: _vm.menuList[item.menu_link]
        }
      }
    }, [_vm._v(_vm._s(item.menu_name))]), _vm._v(" "), _vm._m(0, true)] : _vm._e(), _vm._v(" "), (item.menu_type == 2) ? [_c('a', {
      staticClass: "nav-item",
      attrs: {
        "href": "javascript:;"
      },
      on: {
        "click": function($event) {
          _vm.openLink(item.menu_link)
        }
      }
    }, [_vm._v(_vm._s(item.menu_name))]), _vm._v(" "), _vm._m(1, true)] : _vm._e()], 2)
  })) : _vm._e(), _vm._v(" "), _c('div', {
    staticClass: "header-personal-wrapper"
  }, [(!_vm.isLogin) ? _c('div', {
    staticClass: "unlogin"
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.jumpout('login')
      }
    }
  }, [_vm._v("登  录")]), _vm._v(" "), _c('el-button', {
    staticClass: "hand-btn",
    on: {
      "click": function($event) {
        _vm.jumpout('reg')
      }
    }
  }, [_vm._v("注  册")])], 1) : _vm._e(), _vm._v(" "), (_vm.isLogin) ? _c('div', {
    staticClass: "logined"
  }, [_c('el-dropdown', {
    staticClass: "cart-dropdown-wrapper",
    attrs: {
      "menu-align": "start"
    },
    on: {
      "visible-change": function (flag) {
        _vm.ctrlActiveLineWithDropdown(flag, '.cart-dropdown-wrapper')
      }
    }
  }, [_c('span', {
    staticClass: "cart-wrapper pointer"
  }, [_c('el-badge', {
    staticClass: "item",
    attrs: {
      "value": _vm.totalCount
    }
  }, [_c('i', {
    staticClass: "iconfont icon-gouwuche",
    on: {
      "click": _vm.fnGoShop
    }
  })]), _vm._v(" "), _c('span', {
    staticClass: "active-line"
  }, [_c('i')])], 1), _vm._v(" "), _c('el-dropdown-menu', {
    staticClass: "cart-nav-dropdown",
    attrs: {
      "slot": "dropdown"
    },
    slot: "dropdown"
  }, [(_vm.totalCount != 0) ? [_c('p', {
    staticClass: "header",
    class: _vm.shopPreviewList.length == 0 ? 'null-preview-header' : ''
  }, [_vm._v("共" + _vm._s(_vm.totalCount) + "个广告位\n                            "), _c('span', [_vm._v("已失效" + _vm._s(_vm.invalidCount) + "个广告位")])]), _vm._v(" "), _c('ul', {
    staticClass: "cart-list-wrapper"
  }, _vm._l((_vm.shopPreviewList), function(item, index) {
    return _c('li', {
      key: index
    }, [_c('span', {
      staticClass: "pic",
      style: ({
        'background-image': 'url(' + item.channel_head + ')'
      })
    }), _vm._v(" "), _c('p', {
      staticClass: "content ellipsis"
    }, [_vm._v(_vm._s(item.channel_name) + "\n                                    "), _c('br'), _vm._v(" "), _c('span', [_vm._v(_vm._s(item.cate_title) + " - " + _vm._s(item.field_title))])]), _vm._v(" "), _c('p', {
      staticClass: "price-and-operation"
    }, [_c('span', [_vm._v("￥" + _vm._s(item.space_price))]), _vm._v(" "), _c('el-button', {
      staticClass: "del-btn",
      attrs: {
        "type": "text",
        "disabled": _vm.disabledList[item.space_no]
      },
      on: {
        "click": function($event) {
          _vm.fnDelShopListItem($event, item.space_no)
        }
      }
    }, [_vm._v("删除")])], 1)])
  })), _vm._v(" "), _c('div', {
    staticClass: "footer clearfix"
  }, [_c('p', {
    staticClass: "pay-tax"
  }, [_vm._v("税费：￥" + _vm._s(_vm.carPayTax))]), _vm._v(" "), _c('p', [_vm._v("总额￥" + _vm._s(_vm.totalPrice))]), _vm._v(" "), _c('el-dropdown-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.fnGoShopCar
    }
  }, [(_vm.shopPreviewList.length == 0) ? [_vm._v("去购物车修改")] : _vm._e(), _vm._v(" "), (_vm.shopPreviewList.length != 0) ? [_vm._v("去购物车提交")] : _vm._e()], 2)], 1)], 1)] : [_c('div', {
    staticClass: "preview-null"
  }, [_vm._v("\n                            还没有广告位，快去添加吧！\n                        ")])]], 2)], 1), _vm._v(" "), _c('router-link', {
    staticClass: "message-wrapper",
    class: {
      'reddot': _vm.hasNewMessage
    },
    attrs: {
      "to": "/message"
    }
  }, [_c('i', {
    staticClass: "iconfont icon-lingdang"
  }), _vm._v(" "), _c('span', {
    staticClass: "active-line"
  }, [_c('i')])]), _vm._v(" "), _c('el-dropdown', {
    staticClass: "user-head-wrapper",
    attrs: {
      "menu-align": "start"
    },
    on: {
      "visible-change": function (flag) {
        _vm.ctrlActiveLineWithDropdown(flag, '.user-head-wrapper')
      }
    }
  }, [_c('span', {
    staticClass: "user-head-img pointer",
    class: {
      'iconfont icon-duihao': _vm.userInfo && _vm.userInfo.auth_status == 2
    }
  }, [_c('img', {
    attrs: {
      "src": _vm.companyInfo.logo || 'http://cdn.wxhand.com/wei/images/hire/common/company_default.png',
      "alt": ""
    }
  }), _vm._v(" "), _c('span', {
    staticClass: "active-line"
  }, [_c('i')])]), _vm._v(" "), _c('el-dropdown-menu', {
    staticClass: "header-nav-dropdown",
    attrs: {
      "slot": "dropdown"
    },
    slot: "dropdown"
  }, [_c('el-dropdown-item', [_c('a', {
    staticClass: "company-name",
    attrs: {
      "href": "javascript:;"
    }
  }, [_vm._v(_vm._s(_vm.companyInfo.name))])]), _vm._v(" "), _c('p', {
    staticClass: "dropdown-divide-line"
  }), _vm._v(" "), _c('el-dropdown-item', [_c('router-link', {
    attrs: {
      "to": {
        name: 'Wallet'
      }
    }
  }, [_vm._v("钱包")])], 1), _vm._v(" "), _c('el-dropdown-item', [_c('router-link', {
    attrs: {
      "to": {
        name: 'MaterialList'
      },
      "target": "_blank"
    }
  }, [_vm._v("素材库")])], 1), _vm._v(" "), _c('el-dropdown-item', [_c('router-link', {
    attrs: {
      "to": {
        name: 'Invoice'
      }
    }
  }, [_vm._v("广告发票")])], 1), _vm._v(" "), _c('el-dropdown-item', [_c('router-link', {
    attrs: {
      "to": {
        name: 'Account'
      }
    }
  }, [_vm._v("我的账号")])], 1), _vm._v(" "), _c('el-dropdown-item', [_c('a', {
    attrs: {
      "href": "javascript:;"
    },
    on: {
      "click": _vm.logout
    }
  }, [_vm._v("退出")])])], 1)], 1), _vm._v(" "), _c('el-button', {
    staticClass: "hand-btn",
    on: {
      "click": _vm.fnGoAdvManage
    }
  }, [_vm._v("我的广告")])], 1) : _vm._e()])], 1)
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('span', {
    staticClass: "active-line"
  }, [_c('i')])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('span', {
    staticClass: "active-line"
  }, [_c('i')])
}]}

/***/ }),

/***/ 250:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    directives: [{
      name: "clickoutside",
      rawName: "v-clickoutside",
      value: (_vm.hide),
      expression: "hide"
    }],
    staticClass: "customer-service-container"
  }, [_c('el-popover', {
    ref: "verify",
    attrs: {
      "placement": "left",
      "width": "160",
      "popper-class": "verify-qr-image",
      "trigger": "hover"
    },
    model: {
      value: (_vm.showQrImage),
      callback: function($$v) {
        _vm.showQrImage = $$v
      },
      expression: "showQrImage"
    }
  }, [_c('img', {
    staticClass: "qr-image-app",
    attrs: {
      "src": _vm.qrUrl,
      "alt": ""
    }
  }), _vm._v(" "), _c('p', [_vm._v("扫一扫关注公众号")]), _vm._v(" "), _c('p', [_vm._v("随时随地掌握广告动态")])]), _vm._v(" "), _c('div', {
    directives: [{
      name: "popover",
      rawName: "v-popover:verify",
      arg: "verify"
    }, {
      name: "show",
      rawName: "v-show",
      value: (_vm.hideFlag),
      expression: "hideFlag"
    }],
    staticClass: "mini-size-qr"
  }, [_c('i', {
    staticClass: "icon iconfont icon-shouji-copy"
  }), _vm._v("在手机上看\n    ")]), _vm._v(" "), _c('div', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (!_vm.flag),
      expression: "!flag"
    }],
    staticClass: "mini-size-wrapper",
    on: {
      "click": _vm.open
    }
  }, [_c('span', [_vm._v("客服")]), _vm._v("\n        帮助\n    ")]), _vm._v(" "), _c('transition', {
    attrs: {
      "name": "slide"
    }
  }, [_c('div', {
    directives: [{
      name: "show",
      rawName: "v-show",
      value: (_vm.flag),
      expression: "flag"
    }],
    staticClass: "large-size-wrapper"
  }, [(_vm.agentInfo && _vm.agentInfo.agent_site.service_phone) ? _c('a', {
    attrs: {
      "href": "javascript:;"
    }
  }, [_c('i', {
    staticClass: "iconfont icon-dianhua1",
    on: {
      "click": _vm.showPhone
    }
  })]) : _vm._e(), _vm._v(" "), (_vm.agentInfo && (_vm.agentInfo.agent_site.service_type == 1 || (_vm.agentInfo.agent_site.service_type != 1 && _vm.agentInfo.agent_site.service_extra))) ? _c('a', {
    attrs: {
      "href": "javascript:;"
    }
  }, [_c('i', {
    staticClass: "iconfont icon-service",
    on: {
      "click": _vm.showService
    }
  })]) : _vm._e(), _vm._v(" "), _c('router-link', {
    attrs: {
      "to": {
        name: 'help'
      },
      "target": "_blank"
    }
  }, [_c('i', {
    staticClass: "iconfont icon-help",
    on: {
      "click": _vm.hide
    }
  })])], 1)]), _vm._v(" "), (_vm.serviceType == 3) ? _c('el-dialog', {
    attrs: {
      "title": "联系方式",
      "visible": _vm.dialogWechat,
      "size": "tiny",
      "custom-class": "dialog-wechat"
    },
    on: {
      "update:visible": function($event) {
        _vm.dialogWechat = $event
      }
    }
  }, [_c('div', {
    staticClass: "wechat-wrapper"
  }, [_c('div', [_c('img', {
    attrs: {
      "src": _vm.agentInfo && _vm.agentInfo.agent_site.service_extra,
      "alt": ""
    }
  })]), _vm._v(" "), _c('p', [_vm._v("微信扫一扫")])])]) : _vm._e()], 1)
},staticRenderFns: []}

/***/ }),

/***/ 251:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    attrs: {
      "id": "app"
    }
  }, [(_vm.showHeader) ? _c('nav-header') : _vm._e(), _vm._v(" "), _c('router-view'), _vm._v(" "), _c('customer-service')], 1)
},staticRenderFns: []}

/***/ }),

/***/ 252:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return (_vm.visble) ? _c('div', {
    staticClass: "newuser-alert-container"
  }, [_c('div', {
    staticClass: "content-wrapper"
  }, [(_vm.step == 1) ? _c('div', {
    staticClass: "step-1-wrapper"
  }, [_c('div', {
    staticClass: "step-1-close",
    on: {
      "click": function($event) {
        _vm.visble = false
      }
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "step-1-next",
    on: {
      "click": function($event) {
        _vm.step = 2
      }
    }
  })]) : _vm._e(), _vm._v(" "), (_vm.step == 2) ? _c('div', {
    staticClass: "step-2-wrapper"
  }, [_c('div', {
    staticClass: "step-2-close",
    on: {
      "click": function($event) {
        _vm.visble = false
      }
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "step-2-next",
    on: {
      "click": function($event) {
        _vm.step = 3
      }
    }
  })]) : _vm._e(), _vm._v(" "), (_vm.step == 3) ? _c('div', {
    staticClass: "step-3-wrapper"
  }, [_c('div', {
    staticClass: "step-2-close",
    on: {
      "click": function($event) {
        _vm.visble = false
      }
    }
  })]) : _vm._e()])]) : _vm._e()
},staticRenderFns: []}

/***/ }),

/***/ 7:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export FILTER_SELECTED */
/* unused harmony export FILTER_CLEAR */
/* unused harmony export FILTER_DELETE */
/* unused harmony export HAS_CAMPUS */
/* unused harmony export SHOP_SELECTED_LIST */
/* unused harmony export MODELS_BASE */
/* unused harmony export SET_CAMPUS_LIST */
/* unused harmony export ORDER_OPERATE */
/* unused harmony export ORDER_LIST */
/* unused harmony export REFRESH_ORDER_LIST */
/* unused harmony export FILTER_FRIENDS */
/* unused harmony export FILTER_CLEAR_FRIEND */
/* unused harmony export DEL_FRIEND_RESULT */
/* unused harmony export GET_SHOP_PREVIEW_LIST */
/* unused harmony export GET_SHOP_LIST */
/* unused harmony export ADVERTISE_CONTENT */
/* unused harmony export SET_ADVERTISE_CONTENT */
/* unused harmony export GET_CHANNEL_LIST */
/* unused harmony export CHANGE_BASE_CITY */
/* unused harmony export REFRESH_FRIENDS_LIST */
/* unused harmony export SET_TAG_LISTS */
/* unused harmony export CLEAR_SELECTED_LIST */
/*
 * action-types 在此配置actions的type
 * @Author: ice
 * @Date: 2017-07-24 19:21:41
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-15 16:20:43
 */

//用户已经筛选的条件
var FILTER_SELECTED = 'FILTER_SELECTED';

//清空已选条件
var FILTER_CLEAR = 'FILTER_CLEAR';
var FILTER_DELETE = 'FILTER_DELETE';
var HAS_CAMPUS = 'HAS_CAMPUS';

//购物车已选渠道列表
var SHOP_SELECTED_LIST = 'SHOP_SELECTED_LIST';
//基础models对照表
var MODELS_BASE = 'MODELS_BASE';

// 设置学校列表
var SET_CAMPUS_LIST = 'SET_CAMPUS_LIST';
//操作订单列表
var ORDER_OPERATE = 'ORDER_OPERATE';
// 订单列表
var ORDER_LIST = 'ORDER_LIST';
// 是否需要刷新订单列表
var REFRESH_ORDER_LIST = 'REFRESH_ORDER_LIST';
//筛选朋友圈
var FILTER_FRIENDS = 'FILTER_FRIENDS';

// 清除筛选朋友圈
var FILTER_CLEAR_FRIEND = 'FILTER_CLEAR_FRIEND';
//删除搜索结果
var DEL_FRIEND_RESULT = 'DEL_FRIEND_RESULT';
//获取购物车预览列表
var GET_SHOP_PREVIEW_LIST = 'GET_SHOP_PREVIEW_LIST';
//获取购物车列表
var GET_SHOP_LIST = 'GET_SHOP_LIST';

//广告内容
var ADVERTISE_CONTENT = 'ADVERTISE_CONTENT';
// 设置广告内容
var SET_ADVERTISE_CONTENT = 'SET_ADVERTISE_CONTENT';
//获取派单渠道列表
var GET_CHANNEL_LIST = 'GET_CHANNEL_LIST';
//获取核心城市和热门城市
var CHANGE_BASE_CITY = 'CHANGE_BASE_CITY';
//刷新朋友圈列表
var REFRESH_FRIENDS_LIST = 'REFRESH_FRIENDS_LIST';
//获取标签列表
var SET_TAG_LISTS = 'SET_TAG_LISTS';
//清空订单已选列表
var CLEAR_SELECTED_LIST = 'CLEAR_SELECTED_LIST';

/***/ }),

/***/ 8:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SET_IS_LOGIN; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "e", function() { return SET_USER_INFO; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "d", function() { return SET_COMPANY_INFO; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "s", function() { return FILTER_SELECTED; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "t", function() { return FILTER_CLEAR; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "u", function() { return FILTER_DELETE; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "v", function() { return HAS_CAMPUS; });
/* unused harmony export SHOP_SELECTED_LIST */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "x", function() { return MODELS_BASE; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "w", function() { return SET_CAMPUS_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "o", function() { return ORDER_OPERATE; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "p", function() { return ORDER_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "q", function() { return REFRESH_ORDER_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "k", function() { return FILTER_FRIENDS; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "l", function() { return FILTER_CLEAR_FRIEND; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "m", function() { return DEL_FRIEND_RESULT; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "i", function() { return GET_SHOP_PREVIEW_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "j", function() { return GET_SHOP_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "g", function() { return ADVERTISE_CONTENT; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "h", function() { return SET_ADVERTISE_CONTENT; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "A", function() { return GET_CHANNEL_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "y", function() { return CHANGE_BASE_CITY; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "n", function() { return REFRESH_FRIENDS_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "f", function() { return PAY_DONE_INFO; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "z", function() { return SET_TAG_LISTS; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "r", function() { return CLEAR_SELECTED_LIST; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return SET_UNREAD_MES; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return SET_AGENT_INFO; });
/*
 * mutation-types 在此配置mutations的type
 * @Author: ice
 * @Date: 2017-07-24 19:21:07
 * @Last Modified by: zhaoyi
 * @Last Modified time: 2017-09-16 18:00:25
 */
// 登陆
var SET_IS_LOGIN = 'SET_IS_LOGIN';
// 用户信息
var SET_USER_INFO = 'SET_USER_INFO';
// 公司信息
var SET_COMPANY_INFO = 'SET_COMPANY_INFO';

//用户已经筛选的条件
var FILTER_SELECTED = 'FILTER_SELECTED';

//清空已选条件
var FILTER_CLEAR = 'FILTER_CLEAR';
var FILTER_DELETE = 'FILTER_DELETE';
var HAS_CAMPUS = 'HAS_CAMPUS';

//购物车已选渠道列表
var SHOP_SELECTED_LIST = 'SHOP_SELECTED_LIST';
//基础models对照表
var MODELS_BASE = 'MODELS_BASE';
// 设置学校列表
var SET_CAMPUS_LIST = 'SET_CAMPUS_LIST';
//操作订单列表
var ORDER_OPERATE = 'ORDER_OPERATE';
// 订单列表
var ORDER_LIST = 'ORDER_LIST';
// 是否需要刷新订单列表
var REFRESH_ORDER_LIST = 'REFRESH_ORDER_LIST';
//筛选朋友圈
var FILTER_FRIENDS = 'FILTER_FRIENDS';
// 清除筛选朋友圈
var FILTER_CLEAR_FRIEND = 'FILTER_CLEAR_FRIEND';
//删除搜索结果
var DEL_FRIEND_RESULT = 'DEL_FRIEND_RESULT';
//获取购物车预览列表
var GET_SHOP_PREVIEW_LIST = 'GET_SHOP_PREVIEW_LIST';
//获取购物车列表
var GET_SHOP_LIST = 'GET_SHOP_LIST';
//广告内容
var ADVERTISE_CONTENT = 'ADVERTISE_CONTENT';
// 设置广告内容
var SET_ADVERTISE_CONTENT = 'SET_ADVERTISE_CONTENT';
//获取派单渠道列表
var GET_CHANNEL_LIST = 'GET_CHANNEL_LIST';
//获取核心城市和热门城市
var CHANGE_BASE_CITY = 'CHANGE_BASE_CITY';
//刷新朋友圈列表
var REFRESH_FRIENDS_LIST = 'REFRESH_FRIENDS_LIST';
// 支付成功跳转信息
var PAY_DONE_INFO = 'PAY_DONE_INFO';
//获取标签列表
var SET_TAG_LISTS = 'SET_TAG_LISTS';
//清空订单已选列表
var CLEAR_SELECTED_LIST = 'CLEAR_SELECTED_LIST';
//未读消息
var SET_UNREAD_MES = 'SET_UNREAD_MES';
//代理信息
var SET_AGENT_INFO = 'SET_AGENT_INFO';

/***/ }),

/***/ 91:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties__ = __webpack_require__(72);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties__);

function plugin(Vue) {
    __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties___default()(Vue.prototype, {
        $api: {
            get: function get() {
                return {
                    /* 派单模块 */
                    'dispatch': {
                        'test': '123'
                    }
                };
            }
        }
    });
}
/* harmony default export */ __webpack_exports__["a"] = (plugin);

/***/ }),

/***/ 92:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties__ = __webpack_require__(72);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_json_stringify__ = __webpack_require__(103);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_json_stringify___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_json_stringify__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise__ = __webpack_require__(181);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_babel_runtime_helpers_typeof__ = __webpack_require__(184);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_babel_runtime_helpers_typeof___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_babel_runtime_helpers_typeof__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_axios__ = __webpack_require__(140);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_axios__);




/*
 * @Author: zhaoyi
 * @Date:   2017-07-21 11:40:03
 * @Last Modified by: wangenbo
 * @Last Modified time: 2017-09-30 14:22:02
 */
/* eslint-disable */




var sessionTicket = '';
var initGetSessionTicketFromUrlFlag = false; // 是否在应用初始化的时候从url中获取过sessionTicket
var getSessionTicketUrl = '/Basic/createSessionTicket';
var gettingSessionTicket = false;
var getSessionTicketTimes = 0;

function postDataStrFormat(content) {
    // return content.toString().replace(/&/g, '%26');
    return encodeURI(content.toString()).replace(/&/g, '%26');
}

function postDataFormatResultMerge(postDataRes, appendPostDataRes) {
    var result = postDataRes;

    if (result != '') {
        result += '&';
    }
    result += appendPostDataRes;

    return result;
}

function postDataFormat(data, name) {
    var postDataRes = '';
    var appendPostDataRes = '';

    if ((typeof data === 'undefined' ? 'undefined' : __WEBPACK_IMPORTED_MODULE_3_babel_runtime_helpers_typeof___default()(data)) == 'object') {
        if (data.length >= 0) {
            for (var i = 0; i < data.length; i++) {
                appendPostDataRes = postDataFormat(data[i], name + '[]', postDataRes);
                postDataRes = postDataFormatResultMerge(postDataRes, appendPostDataRes);
            }
        } else {
            for (var key in data) {
                if (name == '') {
                    appendPostDataRes = postDataFormat(data[key], key);
                } else {
                    appendPostDataRes = postDataFormat(data[key], name + '[' + key + ']');
                }
                postDataRes = postDataFormatResultMerge(postDataRes, appendPostDataRes);
            }
        }
    } else {
        postDataRes += postDataStrFormat(name) + '=' + postDataStrFormat(data);
    }

    return postDataRes;
}

function getSessionTicketLocal() {
    if (!sessionTicket) {
        sessionTicket = localStorage.sessionTicket;
    }

    return sessionTicket;
}

function getSessionTicketFromUrl() {
    var query = parseQueryString(location.search);

    return query ? query.session_ticket : undefined;
}

function getSessionTicketRemote(url, data, success, fail, instance) {
    if (getSessionTicketTimes > 10) {
        __WEBPACK_IMPORTED_MODULE_4_vue__["default"].prototype.$notify.error({
            title: '错误',
            message: '服务器错误，请稍后重试'
        });
        return;
    }
    gettingSessionTicket = true;
    post(getSessionTicketUrl, {}, function (res) {
        if (res.code == 1) {
            sessionTicket = res.data.session_ticket;
            // 本地存储
            localStorage.sessionTicket = sessionTicket;
        }
        gettingSessionTicket = false;
        getSessionTicketTimes++;
        instance.$post(url, data, success, fail, sessionTicket);
    }, function (res) {
        gettingSessionTicket = false;
        getSessionTicketTimes++;
    });
}

function clearSessionTicket() {
    sessionTicket = undefined;
    localStorage.sessionTicket = undefined;
}

__WEBPACK_IMPORTED_MODULE_5_axios___default.a.defaults['withCredentials'] = true;
var axios = __WEBPACK_IMPORTED_MODULE_5_axios___default.a.create({
    baseURL: window.location.host == 'gt.wxhand.com' ? "http://gtapi.wxhand.com/hire" : "http://api.zsdx.cn/hire",
    transformRequest: [function (data, headers) {
        var resData = data;

        if (!(resData instanceof FormData)) {
            resData = postDataFormat(resData, '');
        }
        return resData;
    }],
    method: 'post'
});

axios.interceptors.request.use(function (config) {
    // 这里的config包含每次请求的内容
    // console.log(config);
    config.headers['zsdx-session-ticket'] = sessionTicket;
    config.headers['zsdx-origin-name'] = window.location.host;
    // 本地调试
    if (window.location.host == 'localhost:8080') {
        config.headers['zsdx-origin-name'] = 'gt.wxhand.com';
    }
    return config;
}, function (err) {
    return __WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise___default.a.reject(err);
});

function post(url, data, success, fail, session_ticket) {
    var instance = this;
    var st = session_ticket;

    if (!st && url != getSessionTicketUrl) {
        if (gettingSessionTicket) {
            setTimeout(function () {
                instance.$post(url, data, success, fail);
            }, 200);
            return;
        }
        st = getSessionTicketLocal();
        if (st) {
            instance.$post(url, data, success, fail, st);
        } else {
            getSessionTicketRemote(url, data, success, fail, instance);
        }
    } else {
        axios.post(url, data).then(function (resp) {
            if (resp.status != '200') {
                return __WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise___default.a.reject(res);
            }

            // sessionTicket缺失或者失效
            if (resp.data.code == 1001) {
                var errlog = {
                    time: new Date(),
                    session_ticket: session_ticket,
                    url: url,
                    resp: resp
                };

                localStorage.setItem('errlog1001', __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_json_stringify___default()(errlog));
                if (gettingSessionTicket) {
                    setTimeout(function () {
                        instance.$post(url, data, success, fail);
                    }, 200);
                    return;
                }
                getSessionTicketRemote(url, data, success, fail, instance);
                return;
            }

            // 未登录
            if (resp.data.code == 1002) {
                var _errlog = {
                    time: new Date(),
                    session_ticket: session_ticket,
                    url: url,
                    resp: resp
                };

                localStorage.setItem('errlog1002', __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_json_stringify___default()(_errlog));
                instance.$router.push({
                    name: 'login',
                    query: {
                        dist: {
                            pageName: instance.$route.name,
                            query: instance.$route.query
                        }
                    }
                });
                return;
            }

            // 用户未审核或者审核未通过
            if (resp.data.code == 1004 || resp.data.code == 1005) {
                instance.$notify({
                    type: 'warning',
                    title: '警告',
                    message: '您的账户' + resp.data.code == 1004 ? '正在审核中，请等待处理后再操作' : '审核未通过'
                });
                return;
            }

            // 未绑定手机号
            if (resp.data.code == 1006) {
                instance.$router.push({
                    name: 'bind',
                    query: {
                        dist: {
                            pageName: instance.$route.name,
                            query: instance.$route.query
                        }
                    }
                });
                return;
            }

            // 代理信息相关提示
            if (resp.data.code == 1007 || resp.data.code == 1008) {
                instance.$notify({
                    type: 'warning',
                    title: '警告',
                    message: '代理信息错误，请联系客服'
                });
                return;
            }

            if (success) {
                success(resp.data);
            }
        }).catch(function (err) {
            if (fail) {
                fail(err);
            }
            return __WEBPACK_IMPORTED_MODULE_2_babel_runtime_core_js_promise___default.a.reject(err);
        });
    }
}

function install(Vue, options) {
    Vue.axios = __WEBPACK_IMPORTED_MODULE_5_axios___default.a;

    __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_define_properties___default()(Vue.prototype, {
        $post: {
            get: function get() {
                return post.bind(this);
            }
        },
        axios: {
            get: function get() {
                return axios;
            }
        },
        clearSessionTicket: {
            get: function get() {
                return clearSessionTicket;
            }
        }
    });
}

/* harmony default export */ __webpack_exports__["a"] = ({
    install: install
});

/***/ }),

/***/ 93:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__(253);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__conf_js__ = __webpack_require__(161);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__conf_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2__conf_js__);



__WEBPACK_IMPORTED_MODULE_0_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);

// console.log(conf);

/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({
    mode: "history",
    // routes: [
    //     {
    //         path: '/',
    //         name: 'Hello',
    //         component: Hello
    //     }
    // ]
    routes: __WEBPACK_IMPORTED_MODULE_2__conf_js___default.a
}));

/***/ }),

/***/ 94:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vuex__ = __webpack_require__(15);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__modules_dispatch__ = __webpack_require__(167);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__modules_bidding__ = __webpack_require__(163);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__modules_models__ = __webpack_require__(170);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__modules_order__ = __webpack_require__(171);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__modules_friends__ = __webpack_require__(168);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__modules_car__ = __webpack_require__(164);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__modules_content__ = __webpack_require__(166);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__modules_pay__ = __webpack_require__(172);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__modules_user_info__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__modules_company_info__ = __webpack_require__(165);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__modules_unread_mes__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__modules_agent_info__ = __webpack_require__(162);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__modules_is_login__ = __webpack_require__(169);
















__WEBPACK_IMPORTED_MODULE_0_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_1_vuex__["b" /* default */]);
/* harmony default export */ __webpack_exports__["a"] = (new __WEBPACK_IMPORTED_MODULE_1_vuex__["b" /* default */].Store({
    modules: {
        dispatch: __WEBPACK_IMPORTED_MODULE_2__modules_dispatch__["a" /* default */],
        models: __WEBPACK_IMPORTED_MODULE_4__modules_models__["a" /* default */],
        bidding: __WEBPACK_IMPORTED_MODULE_3__modules_bidding__["a" /* default */],
        order: __WEBPACK_IMPORTED_MODULE_5__modules_order__["a" /* default */],
        friends: __WEBPACK_IMPORTED_MODULE_6__modules_friends__["a" /* default */],
        car: __WEBPACK_IMPORTED_MODULE_7__modules_car__["a" /* default */],
        content: __WEBPACK_IMPORTED_MODULE_8__modules_content__["a" /* default */],
        pay: __WEBPACK_IMPORTED_MODULE_9__modules_pay__["a" /* default */],
        userInfo: __WEBPACK_IMPORTED_MODULE_10__modules_user_info__["a" /* default */],
        companyInfo: __WEBPACK_IMPORTED_MODULE_11__modules_company_info__["a" /* default */],
        unreadMes: __WEBPACK_IMPORTED_MODULE_12__modules_unread_mes__["a" /* default */],
        agentInfo: __WEBPACK_IMPORTED_MODULE_13__modules_agent_info__["a" /* default */],
        isLogin: __WEBPACK_IMPORTED_MODULE_14__modules_is_login__["a" /* default */]
    }
}));

/***/ }),

/***/ 97:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 98:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 99:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ })

},[160]);