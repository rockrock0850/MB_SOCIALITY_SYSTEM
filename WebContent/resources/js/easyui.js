var autoCompleteEditorCache = {};
var combogridCache = {};
$
.extend(
    $.fn.datagrid.defaults.editors,
    {
        text : {
            init : function(container, options) {
                var input = $('<input type="text" class="form-control" style="margin:3px;">').appendTo(container);
                if (options != undefined) {
                input.attr(options);
            }
        return input;
    },
    destroy : function(target) {
        $(target).remove();
    },
    setValue : function(target, value) {
        $(target).val(value);
    },
    getValue : function(target) {
        return $(target).val();
    },
    resize : function(target, width) {
        $(target)._outerWidth(width - 6);
    }
},
label : {
    init : function(container, options) {
        var input = $('<span style="margin: 3px";></span>')
                .appendTo(container);
        return input;
    },
    destroy : function(target) {
        $(target).remove();
    },
    setValue : function(target, value) {
        $(target).html(value);
    },
    getValue : function(target) {
        return $(target).html();
    }
},
file : {
    init : function(container, options) {
        var input = $('<span style="margin:3px;"></span>');
    if (options != undefined) {
        $.each(options, function(opt, val) {
            input.attr(opt, val);
        });
    } else {
        $.messager
            .alert(
                'Config error',
                '隢见��身摰� editor => {type:"file", options: {mainGuid: "XXX", done: "function name", fail: "function name"}}',
                'warning');
        return false;
    }
    input.appendTo(container);
    return input;
},
destroy : function(target) {
    $(target).remove();
},
setValue : function(target, value) {
    if (value != '' && value != undefined) {
        $(target).html(value);
    } else {
        $(target).html(
            '<input type="file" style="margin:3px;" onChange="dataGridFileUpload(this, event, '
                    + $(target).attr('done') + ', ' + $(target).attr('fail') + ')">');
        }
    },
    getValue : function(target) {
        return $(target).html();
    },
    resize : function(target, width) {
        $(target)._outerWidth(width - 6);
    }
},
date : {
    init : function(container, options) {
        var orientation = 'auto';
        if (options.orientation != undefined) {
            orientation = options.orientation;
        }
        if (options.readOnlyTextField == 'true') {
            var input = $('<input type="text" class="form-control" style="margin:3px;cursor:pointer;" readonly>');
} else {
    var input = $('<input type="text" class="form-control" style="margin:3px;cursor:pointer;">');
        }
        var dataOptions = {
            autoclose : true,
            format : 'yyyy-mm-dd',
            todayHighlight : true,
            orientation : orientation
        };
        input.datepicker(dataOptions);
        input.appendTo(container);
        return input;
    },
    destroy : function(target) {
        $(target).remove();
    },
    setValue : function(target, value) {
        $(target).val(value);
    },
    getValue : function(target) {
        return $(target).val();
    },
    resize : function(target, width) {
        $(target)._outerWidth(width - 6);
    }
},
radio : {
    init : function(container, options) {
        var input = $('<input type="radio" class="form-control" style="margin:3px;">')
        if (options != undefined) {
            input.attr(options);
        }
        input.appendTo(container);
        return input;
    },
    destroy : function(target) {
        $(target).remove();
    },
    setValue : function(target, value) {
        $(target).attr('checked', ($(target).val() == value) ? true : false);
    },
    getValue : function(target) {
        return $(target).is(':checked') ? $(target).val() : '';
    }
},
combogrid : {
    init : function(container, options) {
        var input = $('<select class="form-control" style="margin:3px;"></select>');
        input.appendTo(container);
        var gridOption = {
            height : 34,
            editable : false,
            dataAttr : '',
            onLoadSuccess : function(data) {
                var opts = $(this).combo('options');
                $.each(data.rows, function() {
                    combogridCache[this[opts.idField]] = this;
                });
            }
        };
        $.extend(gridOption, options);
        input.combogrid(gridOption);
        return input;
    },
    destroy : function(target) {
        $(target).remove();
    },
    setValue : function(target, value) {
        var grid = $(target).combogrid('grid');
        var opts = $(target).combogrid('options');
        var valueList = new Array();
        if (value != undefined) {
            for (var i = 0; i < value.length; i++) {
                if (typeof value[i] == 'string') {
                    if (combogridCache[value[i]] != undefined) {
                        grid.datagrid('appendRow', combogridCache[value[i]]);
                    }
                    valueList.push(value[i]);
                } else if (typeof value[i] == 'object') {
                    if (value[i][opts.dataAttr] != undefined) {
                        grid.datagrid('appendRow', value[i][opts.dataAttr]);
                        valueList.push(value[i][opts.dataAttr][opts.idField]);
                    } else {
                        grid.datagrid('appendRow', value[i]);
                        valueList.push(value[i][opts.idField]);
                    }
                }
            }
            $(target).combogrid('setValues', valueList);
        }
    },
    getValue : function(target) {
        return $(target).combogrid('getValues');
    },
    resize : function(target, width) {
        $(target).combo('resize', width - 6);
    }
},
select : {
    init : function(container, options) {
        var input = $('<select class="form-control" style="margin:3px;"></select>');
            $.each(options.data, function(val, text) {
                input.append('<option value="' + val + '">' + text + '</option>');
            });
            input.appendTo(container)
            return input;
        },
        destroy : function(target) {
            $(target).remove();
        },
        setValue : function(target, value) {
            if (typeof value == 'object' && value != null) {
                $(target).val(value.guid);
            } else {
                $(target).val(value);
            }
        },
        getValue : function(target) {
            return $(target).val();
        },
        resize : function(target, width) {
            $(target)._outerWidth(width - 6);
        }
    },
    autoComplete : {
        init : function(container, options) {
            var input = $('<select class="form-control"></select>');
            input.attr(options);
            input.appendTo(container)
            input.combobox({
                height : 34,
                selectOnNavigation : false,
                hasDownArrow : false
            });
            return input;
        },
        destroy : function(target) {
            $(target).remove();
        },
        setValue : function(target, value) {
            if (typeof value == 'object') {
                var opts = target.combo('options');
                var data = {};
                data[opts.valueField] = value[opts.valueField];
                data[opts.textField] = value[opts.textField];
                target.combobox('loadData', [data]);
                target.combobox('setValue', value[opts.valueField]);
            } else if (autoCompleteEditorCache[value] != undefined) {
                target.combobox('loadData', autoCompleteEditorCache[value]);
                target.combobox('setValue', value);
            }
        },
        getValue : function(target) {
            autoCompleteEditorCache[target.combo('getValue')] = target.combobox('getData');
            return target.combo('getValue');
        },
        resize : function(target, width) {
            $(target).combo('resize', width - 6);
        }
    },
});
/**
 * Datagrid Class
 */
var dataGridClass = {
    getNew : function() {

        var datagrid = {};

        // 撌脣㗲�賊��𤌍
        datagrid.selections = null;

        // 閮𦠜�航�𣇉��
        datagrid.msgWindow;

        /**
         * 頛匧�亥���坔�滚�蓥��
         *
         * @see {@link http://www.jeasyui.com/documentation/datagrid.php}
         */
        datagrid.onBeforeLoad = function(param) {
            // 撖怠�丘SRF撽𡑒�凤oken
            if ($('#csrfToken').size()) {
                //param = pushCSRFParams(param);
            }
            if ($('#searchParams').size()) {
                var searchParams = JSON.parse($('#searchParams').val());
                if (searchParams.length == undefined) {
                    return false;
                }
            }
            return param;
        }

        datagrid.onListLoadSuccess = function(datagridObj, data) {
            if (data.total == 0) {
                MessageNoDataFound();
            }
            $('[data-toggle="tooltip"]').tooltip({
                html : true
            });
        }

        datagrid.onFormLoadSuccess = function(datagridObj, data) {
            for (i = 0; i < data.total; i++) {
                $(datagridObj).datagrid('beginEdit', i);
                if (typeof onBeginEditDone == 'function') {
                    onBeginEditDone($(datagridObj), i);
                }
            }

            if ($('#pageEventName').val() == 'view') {
                $(':input').attr('disabled', true);
            } else {
                $(':input').on('click', function() {
                    $(this).select();
                });
            }

            $('[data-toggle="tooltip"]').tooltip({
                html : true
            });
        }

        // ��硋�𦯀�皞鞾�URL
        function getRefererUrl() {
            return encodeURI(window.location.href);
        }
        /**
         * �擧蕪
         *
         * @param object
         *            datagridObj datagrid��隞�
         * @param object
         *            buttonObj ��厰�閧�隞�
         */
        datagrid.filter = function(datagridObj, buttonObj, filter) {
            if (this.filterOpen) {
                datagridObj.datagrid('disableFilter');
                this.filterOpen = false;
            } else {
                this.filterOpen = true;
                if (filter == false) {
                    datagridObj.datagrid('enableFilter');
                } else {
                    datagridObj.datagrid('enableFilter', filter);
                }
            }
        }

        /**
         * 朣坿憚閮剖�𡁻�詨鱓 ��见��/��𣈯��
         *
         * @param object
         *            datagridObj datagrid��隞�
         * @param object
         *            buttonObj ��厰�閧�隞�
         */
        datagrid.config = function(datagridObj, buttonObj) {
            var gridId = datagridObj.attr('id');
            var menuId = datagridObj.attr('id') + 'Menu';
            if (!$('#' + menuId).size()) {
                var regEx = /<[^>]*>/g;
                var menu = '';
                menu += '<ul id="' + menuId + '" class="config">';
                // �脣�䁅身摰𡁏�厰��
                var btnTitle = '閮剖�𡁜�脣��';
                menu += '<li style="float:none;">';
    menu += '<button id="btnConfigSave" class="btn btn-success" title="' + btnTitle
        + '" data-blank="false" style="padding:6px 6px;" >' + btnTitle + '</button>';
            menu += '</li>'
            $.each(datagridObj.datagrid('getColumnFields'), function(fieldKey, fieldName) {
                var fieldInfo = datagridObj.datagrid('getColumnOption', fieldName);
                var checked = 'checked';
                if (fieldInfo.hidden === true) {
                    checked = '';
                }
                var configId = 'config_' + fieldName;
                var label = '<label for="' + configId + '">' + fieldInfo.title.replace(regEx, "") + '</label>';
                // �賊�START
                menu += '<li>';
                menu += '<input type="checkbox" id="' + configId + '" config-column="' + fieldName + '" ' + checked
                    + '>';
                menu += label;
                menu += '</li>'
                // �賊�END
            })
            menu += '</ul>'
            $(buttonObj).after(menu);
            var datagrid = this;
            $('[config-column]').bind('click', function() {
                datagrid.configClick(datagridObj, this);
            })
            $('#btnConfigSave').bind('click', function() {
                datagrid.configSave(datagridObj, this);
            })
            $('body').mouseout(
                function(evt) {
                    if ($(':visible#' + menuId).size()) {
                        if ($(evt.target).parents('#' + menuId).length == 0
                            && buttonObj.parent().find(evt.target).size() == 0 && evt.target.id != menuId) {
                            $('#' + menuId).hide();
                        }
                    }
                });
        } else {
            if ($(':visible#' + menuId).size()) {
                $('#' + menuId).hide();
            } else {
                $('#' + menuId).show();
            }
        }
        if (this.filterOpen) {
            this.filter(datagridObj, buttonObj, false);
        }
    }

    /**
     * 朣坿憚�賊� �㗲��/��𡝗��
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.configClick = function(datagridObj, buttonObj) {
        var field = $(buttonObj).attr('config-column');
        if ($(buttonObj).prop('checked')) {
            datagridObj.datagrid('showColumn', field)
        } else {
            datagridObj.datagrid('hideColumn', field)
        }
    }

    /**
     * 朣坿憚�𤌍��滩身摰𡁜�脣��
     *
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.configSave = function(datagridObj, buttonObj) {
        var data = {};
        //data = pushCSRFParams(data);
        data['controllerId'] = datagridObj.attr('controllerId');
        data['actionId'] = datagridObj.attr('actionId');
        $.each($(':checkbox', $(buttonObj).closest('ul')), function() {
            data['Config[' + $(this).attr('config-column') + ']'] = $(this).prop('checked');
        });
        ajaxRequest({
            url : $('#eventPrefix').val() + 'ConfigSave',
            data : data,
            done : function(responseText) {
                msgCls.fadeMsg(displayMsg.alert, displayMsg.configSave.success, 'success');
            },
            fail : function(jqXHR, textStatus) {
                $.messager.alert(displayMsg.notice, jqXHR.responseText, 'error');
            }
        });
    }

    /**
     * 憿舐內�鰵憓䂿𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     * @param object
     *            query 憿滚�硋��彍
     */
    datagrid.create = function(datagridObj, buttonObj, query) {
        this.openDialog(buttonObj);
        if (query == undefined) {
            query = {};
        }
        query.back = getRefererUrl();
        //alert();                     example: category_create
        //alert('sociality/' + $('#eventPrefix').val()+'_create'); 
        $('#eventDialogFrame').attr('src',$('#eventPrefix').val()+'_create');//'src', $('#eventPrefix').val() + 'Create?' + $.param(query)
    }

    /**
     * 憿舐內蝬剛風�𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     * @param object
     *            query 憿滚�硋��彍
     */
    datagrid.modify = function(datagridObj, buttonObj, query) {
        var selections = this.getSelections(datagridObj);

        if (selections.size == 0) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.modify, 'warning');
            this.selections = null;
        } else {
            var selectionGuid;
            $.each(selections.rows, function(idx, raw) {
                selectionGuid = raw.guid;
                return false;
            });
            this.openDialog(buttonObj);
            if (query == undefined) {
                query = {};
            }
            query.pk = selectionGuid;
            query.back = getRefererUrl();
            $('#eventDialogFrame').attr('src',$('#eventPrefix').val()+'_update/'+selectionGuid);//'src', $('#eventPrefix').val() + 'Modify?' + $.param(query)
        }
    }

    /**
     * 憿舐內�鱓蝑�蝬剛風�𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsModify = function(datagridObj, buttonObj) {
        var clicked = this.getClicked(datagridObj, buttonObj);
        if (clicked === false) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.modify, 'warning');
        } else {
            this.openDialog(buttonObj);
            $('#eventDialogFrame').attr('src',
                $('#eventPrefix').val() + 'Modify?pk=' + clicked.guid + '&back=' + getRefererUrl());
        }
    }

    /**
     * 憿舐內瑼Ｚ�𣇉𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     * @param object
     *            query 憿滚�硋��彍
     */
    datagrid.view = function(datagridObj, buttonObj, query) {
        var selections = this.getSelections(datagridObj);

        if (selections.size == 0) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.view, 'warning');
            this.selections = null;
        } else {
            var selectionGuid;
            $.each(selections.rows, function(idx, raw) {
                selectionGuid = raw.guid;
                return false;
            });
            this.openDialog(buttonObj);
            if (query == undefined) {
                query = {};
            }
            query.pk = selectionGuid;
            query.back = getRefererUrl();
            $('#eventDialogFrame').attr('src',$('#eventPrefix').val()+'_view/'+selectionGuid);//'src', $('#eventPrefix').val() + 'View?' + $.param(query)
        }
    }

    /**
     * 憿舐內�鱓蝑�瑼Ｚ�𣇉𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsView = function(datagridObj, buttonObj) {
        var clicked = this.getClicked(datagridObj, buttonObj);
        if (clicked === false) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.view, 'warning');
        } else {
            this.openDialog(buttonObj);
            $('#eventDialogFrame').attr('src',
                $('#eventPrefix').val() + 'View?pk=' + clicked.guid + '&back=' + getRefererUrl());
        }
    }
    /**
     * 鞈��坔𥲤�枂
     */
    datagrid.exporting = function(datagridObj, buttonObj) {
        var options = $(datagridObj).datagrid('options');
        var params = $(datagridObj).datagrid('options').queryParams;
        delete params[$('#csrfToken').attr('name')];
        $.each(params, function(name, val){
            form.append($('<input type="hidden" />').attr({
                name: name,
                value: val,
            }));
        });
        form.append($('<input type="hidden" />').attr({
            name: $('#csrfToken').attr('name'),
            value: $('#csrfToken').val(),
        }));
        form.append($('<input type="hidden" />').attr({
            name: 'sort',
            value: options.sortName
        }));
        form.append($('<input type="hidden" />').attr({
            name: 'order',
            value: options.sortOrder
        }));
        form.submit();
    }

    /**
     * �鱓蝑�鞈��坔𥲤�枂
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsExporting = function(datagridObj, buttonObj) {
        var clicked = this.getClicked(datagridObj, buttonObj);
        var options = $(datagridObj).datagrid('options');
        var params = {
            'guid' : clicked.guid
        };
        params.sort = options.sortName;
        params.order = options.sortOrder;
        var exportUri = $.param(params) + (($.param(params) == '') ? '' : '&') + 'back=' + getRefererUrl();
        window.open($('#eventPrefix').val() + 'Export?' + exportUri);
    }
    datagrid.deleted = function(datagridObj, buttonObj) {
        var selections = this.getSelections(datagridObj);
        if (selections.size == 0) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.deleted, 'warning');
            this.selections = null;
        } else {
            $("<div class=\"datagrid-mask\"></div>").css({
                display : "block",
                width : "100%",
                height : $(window).height()
            }).appendTo($('body'));
            var msgWidth = ($(window).width() > 500) ? 500 : ($(window).width() - 50);
            var msgHeight = ($(window).height() > 400) ? 400 : ($(window).height() - 50);
            var delConfirm = $.messager.show({
                title : displayMsg.deleted.title,
                msg : '<div id="confirmMsg" style="height:' + (msgHeight - 85) + 'px;overflow:scroll;"></div>',
        showSpeed : 0,
        showType : 'fade',
        timeout : 0,
        width : msgWidth,
        height : msgHeight,
        style : {
            left : ($(window).width() - msgWidth) / 2,
            top : ($(window).height() - msgHeight) / 2,
            right : '',
            bottom : ''
        },
        onBeforeClose : function() {
            $('.datagrid-mask').remove();
        }
    });
    delConfirm.css('padding', 0);
    $('div[id=confirmMsg]', delConfirm).append(
        '<div class="alert alert-danger" style="margin-bottom:0;height: 100%;"><b>'
            + displayMsg.deleted.confirm + '</b><ul></ul></div>');
    var attrLabel = $('#distinguishProperty');
    //console.log(selections.rows);
    $.each(selections.rows, function(idx, val) {
        var displayLabel;
        console.log(datagridObj.datagrid('getColumnFields'));
        var fn = datagridObj.datagrid('getColumnOption', attrLabel.val()).formatter;
        if (fn != undefined) {
            displayLabel = fn.call(displayLabel, val[attrLabel.val()], val, idx);
        } else {
            displayLabel = val[attrLabel.val()];
        }
        displayLabel = val[attrLabel.val()];
        $('ul', delConfirm).append('<li>' + attrLabel.attr('data-label') + ': ' + displayLabel + '</li>');
    });

    this.msgWindow = delConfirm;
    var btns = $('<div style="text-align: right;"></div>');
    btns.append($('<button class="btn btn-danger" style="margin: 5px;" onClick="dgCls.sendDelete(\''
    + datagridObj.attr('id') + '\');"><i class="glyphicon glyphicon-ok"></i> '
        + displayMsg.deleted.button.yes + '</button>'));
    btns
        .append($('<button class="btn btn-default" style="margin: 5px;" onClick="dgCls.closeWindow();"><i class="glyphicon glyphicon-remove"></i> '
                    + displayMsg.deleted.button.no + '</button>'));
            delConfirm.append(btns);
        }
    }

    // ���枂�⏛�膄鞈���
    datagrid.sendDelete = function(gridId) {
        var datagridObj = $('#' + gridId);
        var data = {'pk':JSON.stringify(this.getSelectedPks(datagridObj))};
        var grid = this;
        //data[$('#csrfToken').attr('name')] = $('#csrfToken').val();
//        data['pk'] = this.getSelectedPks(datagridObj);
//        var json="";
//        $.each(data, function(index){
//        	var guid = data[index];
//        	json += guid;
//        });
//        data['pk'] = JSON.stringify(json);
        $.ajax({
            type:'post',
            contentType: "application/json",
            url :'api/'+ $('#eventPrefix').val() + '_delete',
            data : JSON.stringify(data),
            success : function(responseText) {
            	if(responseText.code == "999"){
            		msgCls.fadeMsg(displayMsg.alert, responseText.result);
            	}else{
            		msgCls.fadeMsg(displayMsg.alert, displayMsg.deleted.success, 'success');
            	}
                datagridObj.datagrid('clearSelections');
                datagridObj.datagrid('reload');
            },
            error : function(jqXHR, textStatus) {
                var msg = JSON.parse(jqXHR.responseText);
                if (msg != "") {
                    grid.displayFormMessage(msg);
                }
            }
        });
        this.closeWindow();
    }
    /**
     * 憿舐內�鱓蝑��⏛�膄�𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsDeleted = function(datagridObj, buttonObj) {
        var clicked = this.getClicked(datagridObj, buttonObj);
        if (clicked === false) {
            $.messager.alert(displayMsg.notice, displayMsg.empty.view, 'warning');
        } else {
            $("<div class=\"datagrid-mask\"></div>").css({
                display : "block",
                width : "100%",
                height : $(window).height()
            }).appendTo($('body'));
            var msgWidth = ($(window).width() > 500) ? 500 : ($(window).width() - 50);
            var msgHeight = ($(window).height() > 400) ? 400 : ($(window).height() - 50);
            var delConfirm = $.messager.show({
                title : displayMsg.deleted.title,
                msg : '<div id="confirmMsg" style="height:' + (msgHeight - 85) + 'px;overflow:scroll;"></div>',
        showSpeed : 0,
        showType : 'fade',
        timeout : 0,
        width : msgWidth,
        height : msgHeight,
        style : {
            left : ($(window).width() - msgWidth) / 2,
            top : ($(window).height() - msgHeight) / 2,
            right : '',
            bottom : ''
        },
        onBeforeClose : function() {
            $('.datagrid-mask').remove();
        }
    });
    delConfirm.css('padding', 0);
    $('div[id=confirmMsg]', delConfirm).append(
        '<div class="alert alert-danger" style="margin-bottom:0;height: 100%;"><b>'
            + displayMsg.deleted.confirm + '</b><ul></ul></div>');
    var attrLabel = $('#distinguishProperty');
    $.each([clicked], function(idx, val) {
        var displayLabel;
        var fn = datagridObj.datagrid('getColumnOption', attrLabel.val()).formatter;
        if (fn != undefined) {
            displayLabel = fn.call(displayLabel, val[attrLabel.val()], val, idx);
        } else {
            displayLabel = val[attrLabel.val()];
        }
        $('ul', delConfirm).append('<li>' + attrLabel.attr('data-label') + ': ' + displayLabel + '</li>');
    });

    this.msgWindow = delConfirm;
    var btns = $('<div style="text-align: right;"></div>');
    btns
        .append($('<button class="btn-Crimson_Button" style="margin: 5px;" onClick="dgCls.sendColumnsDelete(\''
    + datagridObj.attr('id')
    + '\',\''
    + clicked.guid
    + '\');"><i class="glyphicon glyphicon-ok"></i> ' + displayMsg.deleted.button.yes + '</button>'));
    btns
        .append($('<button class="btn-LightGray_Button" style="margin: 5px;" onClick="dgCls.closeWindow();"><i class="glyphicon glyphicon-remove"></i> '
                    + displayMsg.deleted.button.no + '</button>'));
            delConfirm.append(btns);
        }
    }

    // ���枂�鱓蝑��⏛�膄鞈���
    datagrid.sendColumnsDelete = function(gridId, pk) {
        var datagridObj = $('#' + gridId);
        var data = {};
        var grid = this;
        //data = pushCSRFParams(data);
        data['pk'] = [pk];
        ajaxRequest({
            url : $('#eventPrefix').val() + 'Delete',
            data : data,
            done : function(responseText) {
                msgCls.fadeMsg(displayMsg.alert, displayMsg.deleted.success, 'success');
                datagridObj.datagrid('clearSelections');
                datagridObj.datagrid('reload');
            },
            fail : function(jqXHR, textStatus) {
                var msg = JSON.parse(jqXHR.responseText);
                if (msg != "") {
                    grid.displayFormMessage(msg);
                }
            }
        });
        this.closeWindow();
    }
    // ��𣈯�㕑�𦠜�航�𣇉��
    datagrid.closeWindow = function() {
        this.selections = null;
        this.msgWindow.window('close');
    }

    /**
     * ��𣈯枤摮埈䰻閰�
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.search = function(datagridObj, buttonObj) {
        var queryParam = handleSearchParam($('#searchBlock'));
        $('.easyui-datagrid').datagrid('load', queryParam)
    }

    /**
     * 憿舐內�脤�擧�𨅯�讠𧞄�𢒰
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.advSearch = function(datagridObj, buttonObj) {
        $('#eventDialog').dialog({
            fit : false,
            title : this.getEventTitle(buttonObj),
            width : 450,
            height : $(window).height() - 100,
            modal : true,
            closed : false,
            closable : true,
            toolbar : false,
            left : $(window).width() / 2 - 225,
            top : 50
        });
        dialogCls.clearOnClosed = false;
        var params = $('.easyui-datagrid').datagrid('options').queryParams;
        delete params[$('#csrfToken').attr('name')];
        $('#eventDialogFrame').attr('src',
            $('#eventPrefix').val() + 'AdvSearch?back=' + getRefererUrl() + '&' + $.param(params));
    }

    /**
     * ��硋�烾�蝐斗�䠷��+��厰�閙�䠷��
     *
     * @param object
     *            obj ��厰�閧�隞�
     * @return string
     */
    datagrid.getEventTitle = function(obj) {
        if (obj.attr('title') == undefined) {
            var btnHtml = $('button', obj).html();
        } else {
            var btnHtml = obj.attr('title');
        }
        return fetchTabTitle() + ' ��' + btnHtml.replace(/(<([^>]+)>)|&nbsp;/ig, "");
    }

    /**
     * ��硋�珳atagrid�㗲�豢��鱓
     *
     * @param object
     *            datagridObj datagrid��隞�
     */
    datagrid.getSelections = function(datagridObj) {
        var dgSelectedItem = {
            rows : {},
            size : 0
        };
        if (this.selections == null) {
            $.each(datagridObj.datagrid('getSelections'), function(idx, raw) {
                dgSelectedItem.rows[raw.rowNumber] = raw;
                dgSelectedItem.size++;
            });
            this.selections = dgSelectedItem;
        }
        return this.selections;
    }

    /**
     * ��硋�珳atagrid�㗲�貉�睃ê̌蝣潭��鱓
     */
    datagrid.getSelectedPks = function(datagridObj) {
        var selections = this.getSelections(datagridObj);
        var pk = datagridObj.datagrid('options').idField;
        var pkList = new Array();
        $.each(selections.rows, function(idx, row) {
            pkList.push(row[pk]);
        });
        return pkList;
    }

    /**
     * ��硋�珳atagrid暺墧�𠰴�𡑒����(暺墧�羓���� 敹����銁撅祆�找�� 憓𧼮��rowIndex 銵券𢒰��埈彍)
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.getClicked = function(datagridObj, buttonObj) {
        var rowIndex = this.getRowIndex(buttonObj);
        if (typeof (rowIndex) == 'undefined') {
            return false;
        } else {
            var dgSelectedItem = {
                rows : {},
                size : 0
            };
            raw = datagridObj.datagrid('getRows')[$(buttonObj).attr('rowIndex')]
            if (this.selections == null) {
                dgSelectedItem.rows[raw.rowNumber] = raw;
                dgSelectedItem.size = 1;
                this.selections = dgSelectedItem;
            }
            return raw;
        }
    }

    /**
     * ��硋�𡑒”�𢒰��埈彍(暺墧�羓���� 敹����銁撅祆�找�� 憓𧼮��rowIndex 銵券𢒰��埈彍)
     */
    datagrid.getRowIndex = function(buttonObj) {
        var rowIndex = $(buttonObj).attr('rowIndex');
        if (typeof (rowIndex) == 'undefined') {
            return false;
        } else {
            return rowIndex;
        }
    }

    /**
     * ��见�毺𧞄�𢒰獢��沲
     */
    datagrid.openDialog = function(obj) {
        $('#eventDialog').dialog({
            fit : true,
            title : "Dialog",//this.getEventTitle(obj)
            modal : true,
            closed : false,
            closable : false,
            buttons : false,
            toolbar : false,
            left : 0,
            top : 0
        });
    }
    /**
     * ��见�笔翰�毺楊頛舀芋撘�
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsCacheEdit = function(datagridObj, buttonObj) {
        var rowIndex = this.getRowIndex(buttonObj);
        datagridObj.datagrid('beginEdit', rowIndex);
        var rows = datagridObj.datagrid('getRows');
        var grid = this;
        $.each(rows, function(index, row) {
            var rowsIndex = datagridObj.datagrid('getRowIndex', row);
            if (rowsIndex != rowIndex
                && $(':visible[ColumnsCache=ColumnsCacheCancel]', $('[rowIndex=' + rowsIndex + ']')).size()) {
                grid.columnsCacheCancel(datagridObj, $(':visible[ColumnsCache=ColumnsCacheCancel]',
                    $('[rowIndex=' + rowsIndex + ']')).parent());
            }
        })
        $('[ColumnsBtn]', $(buttonObj).closest('td')).hide();
        $('[ColumnsCache=ColumnsCacheSave]', $(buttonObj).closest('td')).show();
        $('[ColumnsCache=ColumnsCacheCancel]', $(buttonObj).closest('td')).show();
    }

    /**
     * �脣�睃翰�毺楊頛�
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsCacheSave = function(datagridObj, buttonObj) {
        var rowIndex = this.getRowIndex(buttonObj);
        datagridObj.datagrid('endEdit', rowIndex);
        var raws = datagridObj.datagrid('getChanges');
        var grid = this;
        if (raws.length) {
            var data = {};
            //data = pushCSRFParams(data);
            data['raws'] = raws;
            ajaxRequest({
                url : $('#eventPrefix').val() + 'CacheSave',
                data : data,
                done : function(responseText) {
                    msgCls.fadeMsg(displayMsg.alert, displayMsg.cacheSave.success, 'success');
                    datagridObj.datagrid('clearSelections');
                    // �脣�䀹�𣂼�笔�諹�閧�
                    datagridObj.datagrid('acceptChanges');
                    $('[ColumnsBtn]', $(buttonObj).closest('td')).show();
                    $('[ColumnsCache=ColumnsCacheSave]', $(buttonObj).closest('td')).hide();
                    $('[ColumnsCache=ColumnsCacheCancel]', $(buttonObj).closest('td')).hide();
                },
                fail : function(jqXHR, textStatus) {
                    var msg = JSON.parse(jqXHR.responseText);
                    if (msg != "") {
                        grid.displayFormMessage(msg);
                    }
                    datagridObj.datagrid('rejectChanges');
                }
            });
        }
    }

    /**
     * �脣�睃翰�笔�𡝗��
     *
     * @param object
     *            datagridObj datagrid��隞�
     * @param object
     *            buttonObj ��厰�閧�隞�
     */
    datagrid.columnsCacheCancel = function(datagridObj, buttonObj) {
        var rowIndex = this.getRowIndex(buttonObj);
        datagridObj.datagrid('cancelEdit', rowIndex);
        $('[ColumnsBtn]', $(buttonObj).closest('td')).show();
        $('[ColumnsCache=ColumnsCacheSave]', $(buttonObj).closest('td')).hide();
        $('[ColumnsCache=ColumnsCacheCancel]', $(buttonObj).closest('td')).hide();
    }

    /**
     * sendSearchDatas �訜�∪㗲�豢� true ���枂��𨅯�贝���� false ��鞟內閬��㗲�賊��𤌍 sendConfirmMethod
     * �鸌甈∠Ⅱ隤滚嘑銵峕䲮瘜� undefined �嘑銵� datagrid.sendBatchConfirm | other �嘑銵�
     * confirmMethod
     */
    datagrid.batchConfirm = function(datagridObj, buttonObj, confirmTitle, confirmStyle, sendSearchDatas,
        confirmMethod, sendConfirmMethod) {
        var selections = this.getSelections(datagridObj);
        if (sendSearchDatas == true) {
            if (selections.size == 0) {
                var dgSelectedItem = {
                    rows : {},
                    size : 0
                };
                $.each(datagridObj.datagrid('getRows'), function(idx, raw) {
                    dgSelectedItem.rows[raw.rowNumber] = raw;
                    dgSelectedItem.size++;
                });
                selections = dgSelectedItem;
                this.selections = selections;
            }
        }

        if (selections.size == 0) {
            $.messager.alert(displayMsg.notice, '隢见���豢���刻�' + confirmTitle + '��鞈���', 'warning');
            this.selections = null;
        } else {
            $("<div class=\"datagrid-mask\"></div>").css({
                display : "block",
                width : "100%",
                height : $(window).height()
            }).appendTo($('body'));
            var msgWidth = ($(window).width() > 500) ? 500 : ($(window).width() - 50);
            var msgHeight = ($(window).height() > 400) ? 400 : ($(window).height() - 50);
            var batchConfirm = $.messager.show({
                title : '�鸌甈�' + confirmTitle + '蝣箄��',
                msg : '<div id="confirmMsg" style="height:' + (msgHeight - 85) + 'px;overflow:scroll;"></div>',
        showSpeed : 0,
        showType : 'fade',
        timeout : 0,
        width : msgWidth,
        height : msgHeight,
        style : {
            left : ($(window).width() - msgWidth) / 2,
            top : ($(window).height() - msgHeight) / 2,
            right : '',
            bottom : ''
        },
        onBeforeClose : function() {
            $('.datagrid-mask').remove();
        }
    });
    batchConfirm.css('padding', 0);
    switch (confirmStyle) {
        case 'warning':
            var divCss = 'alert alert-' + confirmStyle;
            var btnCss = 'btn-mango_Button';
            break;
        default:
            var divCss = 'alert alert-' + confirmStyle;
            var btnCss = 'btn btn-' + confirmStyle;
            break;

    }
    $('div[id=confirmMsg]', batchConfirm).append(
        '<div class="' + divCss + '" style="margin-bottom:0;height: 100%;"><b>' + '�糓�炏' + confirmTitle
            + '銝见�𡑒����?' + '</b><ul></ul></div>');
//    var attrLabel = $('#distinguishProperty');
//
//    $.each(selections.rows, function(idx, val) {
//        var distinguishLabel = new Array();
//        distinguishLabel.push(attrLabel.attr('data-label') + ': ' + val[attrLabel.val()]);
//        $('ul', batchConfirm).append('<li>' + distinguishLabel.join(', ') + '</li>');
//    });

    this.msgWindow = batchConfirm;
    var onClick = '';
    if (typeof (sendConfirmMethod) == 'undefined') {
        onClick = 'dgCls.sendBatchConfirm' + '(\'' + confirmTitle + '\',\'' + confirmMethod + '\',\''
            + datagridObj.attr('id') + '\')';
    } else {
        onClick = confirmMethod + '(\'' + datagridObj.attr('id') + '\')';
    }

    var btns = $('<div style="text-align: right;"></div>');
    btns.append($('<button class="' + btnCss + '" style="margin: 5px;" onClick="' + onClick
    + '"><i class="glyphicon glyphicon-ok"></i> ' + '蝣箄��</button>'));
    btns
        .append($('<button class="btn-LightGray_Button" style="margin: 5px;" onClick="dgCls.closeWindow();"><i class="glyphicon glyphicon-remove"></i> '
                        + '��𡝗��</button>'));
                batchConfirm.append(btns);
            }
        }

        // ���枂�鸌甈∠Ⅱ隤�
        datagrid.sendBatchConfirm = function(confirmTitle, method, gridId) {
            var datagridObj = $('#' + gridId);
            var data = {};
            var grid = this;
            //data = pushCSRFParams(data);
            data['pk'] = this.getSelectedPks(datagridObj);
            ajaxRequest({
                url : $('#eventPrefix').val() + method,
                data : data,
                done : function(responseText) {
                    msgCls.fadeMsg(displayMsg.alert, '鞈��坔歇��𣂼��' + confirmTitle, 'success');
                    datagridObj.datagrid('clearSelections');
                    datagridObj.datagrid('reload');
                },
                fail : function(jqXHR, textStatus) {
                    var msg = JSON.parse(jqXHR.responseText);
                    if (msg != "") {
                        grid.displayFormMessage(msg);
                    }
                }
            });
            this.closeWindow();
        }

        /**
         * 憿舐內閮𦠜��
         *
         * @param string
         *            msg 閮𦠜�臬�批捆
         */
        datagrid.displayFormMessage = function(msg) {
            FormMessageOpen(msg);
        }

        return datagrid;
    }
};

/**
 * Dialog Class
 */
var dialogClass = {
    getNew : function() {

        var dialog = {};

        dialog.clearOnClosed = true;

        /**
         * Before open dialog
         */
        dialog.onBeforeOpen = function() {
            $("<div class=\"datagrid-mask\"></div>").css({
                display : "block",
                width : "100%",
                height : $(window).height()
            }).appendTo($('#eventDialog'));

            $("<div class=\"datagrid-mask-msg\" style=\"padding-top: 5px;height: 30px;\"></div>").html(
                displayMsg.loading).appendTo($('#eventDialog')).css({
                display : "block",
                left : ($('#eventDialog').width() / 2) - 40,
                top : (document.body.clientHeight / 2) + $(document).scrollTop()
            });
            return true;
        }

        /**
         * Dialog is opened
         */
        dialog.onOpened = function() {
            var iframe = $('#eventDialogFrame');
            iframe.load(function() {
                $('.datagrid-mask').remove();
                $('.datagrid-mask-msg').remove();
            });
        }

        /**
         * Dialog is closed
         */
        dialog.onClosed = function() {
            if (this.clearOnClosed) {
                $('.easyui-datagrid').datagrid('clearSelections');
                //alert($('.easyui-datagrid').size());
                $('.easyui-datagrid').datagrid('reload');
                dgCls.selections = null;
            }
            this.clearOnClosed = true;
            $('#eventDialogFrame').attr('src', 'about:blank');
            parent.$('.dialog-toolbar').show();
        }

        /**
         * Close dialog
         */
        dialog.close = function(obj) {
            parent.$('#eventDialog').dialog('close');
        }

        /**
         * Cancel to close dialog
         */
        dialog.cancel = function(obj) {
            $.messager.confirm(displayMsg.notice, displayMsg.abort, function(r) {
                if (r) {
                    dialog.close(obj);
                }
            });
        }

        /**
         * Reload dialog content
         */
        dialog.reload = function(obj) {
            $.messager.confirm(displayMsg.notice, displayMsg.abort, function(r) {
                if (r) {
                    if (parent.onBeforeOpen != undefined) {
                        parent.onBeforeOpen();
                    } else {
                        onBeforeOpen();
                    }
                    window.location.reload();
                }
            });
        }

        /**
         * Submit form
         */
        dialog.save = function(obj) {
            if (this.beforeSave(obj) == true) {
                $('form[id=_form]').submit();
            }
        }

        /**
         * Before form submit
         */
        dialog.beforeSave = function(obj) {
            $.each($('.easyui-datagrid'), function(idx, obj) {
                $(obj).datagrid('acceptChanges');
                $('input[type=hidden][name="' + $(obj).attr('for') + '"]').val(
                    JSON.stringify($(obj).datagrid('getData').rows));
            });
            if (typeof beforeSave == 'function') {
                return beforeSave();
            } else {
                return true;
            }
        }

        return dialog;
    }
};
/**
 * Messager Class
 */
var messagerClass = {
    getNew : function() {

        var messager = {};

        messager.fadeMsg = function(title, message, color) {
            if (color == undefined) {
                color = 'warning'
            }

            var style = {
                success : {
                    color : 'success',
                    icon : 'ok-sign'
                },
                warning : {
                    color : 'warning',
                    icon : 'exclamation-sign'
                },
                error : {
                    color : 'danger',
                    icon : 'remove-sign'
                }
            };

            $.messager.show({
                title : title,
                msg : '<div class="alert alert-' + style[color].color
                    + '" style="font-size: 14px;margin: -10px;"><span class="glyphicon glyphicon-' + style[color].icon
                    + '" aria-hidden="true"></span> ' + message + '</div>',
                showType : 'show',
                height : 88,
                timeout : 2000,
                style : {
                    left : '',
                    right : 0,
                    top : 0,
                    bottom : ''
                }
            });
        }

        return messager;
    }
}

/**
 * easyui datagrid 瑼娍���𠰴��
 * 
 * @param obj
 * @param event
 * @returns {Boolean}
 */
function dataGridFileUpload(obj, event, done, fail) {
    if (event.target.files.length < 1) {
        return false;
    }
    $(obj).hide();
    $(obj).after('Uploading...');
    var data = new FormData();
    $.each(event.target.files, function(key, value) {
        data.append(key, value);
    });
    $.each(getCsrfToken(), function(name, token) {
        data.append(name, token);
    });

    $.ajax({
        url : 'dataGridFileUpload',
        type : 'POST',
        data : data,
        cache : false,
        dataType : 'json',
        processData : false,
        contentType : false,
        success : function(response) {
            if (response.error == 0) {
                var rowIdx = $(obj).closest('div').closest('tr').attr('datagrid-row-index');
                var gridObj = $('#' + $(obj).closest('span').attr('mainGrid'));
                gridObj.datagrid('updateRow', {
                    index : parseInt(rowIdx),
                    row : {
                        fileLink : response.name,
                        fileType : response.type,
                        uploadTmp : response.tmp_name,
                        fileName : response.name
                    }
                });
                if (done != undefined && typeof eval(done) == 'function') {
                    var doneFunc = eval(done);
                    doneFunc(response);
                }
            } else {
                $.messager.alert('upload fail', response.error, 'error');
                $(obj).show();
                $(obj).parent().html(obj);
                if (fail != undefined && typeof eval(fail) == 'function') {
                    var failFunc = eval(fail);
                    failFunc(response);
                }
            }
        },
        error : function(jqXHR, textStatus) {
            $(obj).show();
            $.messager.alert('upload error', jqXHR.responseText, 'error');
            if (fail != undefined && typeof eval(fail) == 'function') {
                var failFunc = eval(fail);
                failFunc(jqXHR);
            }
        }
    });
}