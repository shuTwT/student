
(function (win) {
    "use strict";
    const doc = document

        , pureAdmin = function () {
        this.v = '2.2'; //版本号
    };

    pureAdmin.prototype.init = function () {
        var tab_list = this.get_data();
        for (var i in tab_list) {
            this.add_lay_tab(tab_list[i].title, tab_list[i].url, i);
        }
        element.tabChange('xbs_tab', i);
    };
    /**
     * [end 执行结束要做的]
     * @return {[type]} [description]
     */
    pureAdmin.prototype.end = function () {

        var cate_list = this.get_cate_data();

        for (var i in cate_list) {
            if (cate_list[i] != null) {
                $('.left-nav #nav li').eq(cate_list[i]).click();

            }
        }
    };

    pureAdmin.prototype.add_tab = function (title, url, is_refresh) {
        var id = md5(url);//md5每个url
        //const id=url
        //重复点击
        for (var i = 0; i < $('.x-iframe').length; i++) {
            if ($('.x-iframe').eq(i).attr('tab-id') == id) {
                element.tabChange('xbs_tab', id);
                if (is_refresh)
                    $('.x-iframe').eq(i).attr("src", $('.x-iframe').eq(i).attr('src'));
                return;
            }
        }
        ;

        this.add_lay_tab(title, url, id);
        this.set_data(title, url, id);
        element.tabChange('xbs_tab', id);

    }

    pureAdmin.prototype.del_tab = function (id) {

        if (id) {
            console.log(88);
        } else {
            var id = $(window.frameElement).attr('tab-id');
            parent.element.tabDelete('xbs_tab', id);
        }
    }

    pureAdmin.prototype.add_lay_tab = function (title, url, id) {
        element.tabAdd('xbs_tab', {
            title: title
            ,
            content: '<iframe tab-id="' + id + '"  src="' + url + '" class="x-iframe"></iframe>'
            ,
            id: id
        })
    }
    /**
     * [open 打开弹出层]
     * @param  {[type]}  title [弹出层标题]
     * @param  {[type]}  url   [弹出层地址]
     * @param  {[type]}  w     [宽]
     * @param  {[type]}  h     [高]
     * @param  {Boolean} full  [全屏]
     * @return {[type]}        [description]
     */
    pureAdmin.prototype.open = function (title, url, w, h, full) {
        if (title == null || title == '') {
            var title = false;
        }
        ;
        if (url == null || url == '') {
            var url = "404.html";
        }
        ;
        if (w == null || w == '') {
            var w = ($(window).width() * 0.9);
        }
        ;
        if (h == null || h == '') {
            var h = ($(window).height() - 50);
        }
        ;
        var index = layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url
        });
        if (full) {
            layer.full(index);
        }
    }
    /**
     * [close 关闭弹出层]
     * @return {[type]} [description]
     */
    pureAdmin.prototype.close = function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    /**
     * [close 关闭弹出层父窗口关闭]
     * @return {[type]} [description]
     */
    pureAdmin.prototype.father_reload = function () {
        parent.location.reload();
    };
    /**
     * [get_data 获取所有项]
     * @return {[type]} [description]
     */
    pureAdmin.prototype.get_data = function () {
        if (typeof is_remember != "undefined")
            return false;
        return layui.data('tab_list')
    }
    /**
     * [set_data 增加某一项]
     * @param {[type]} id [description]
     */
    pureAdmin.prototype.set_data = function (title, url, id) {

        if (typeof is_remember != "undefined")
            return false;

        layui.data('tab_list', {
            key: id
            , value: {title: title, url: url}
        });
    };

    /**
     * [get_data 获取所有项]
     * @return {[type]} [description]
     */
    pureAdmin.prototype.get_cate_data = function () {
        if (typeof is_remember != "undefined")
            return false;
        return layui.data('cate')
    }
    /**
     * [set_data 增加某一项]
     * @param {[type]} id [description]
     */
    pureAdmin.prototype.set_cate_data = function (data) {

        if (typeof is_remember != "undefined")
            return false;

        layui.data('cate', data);
    };
    /**
     * [del_data 删除某一项]
     * @param  {[type]} id [description]
     * @return {[type]}    [description]
     */
    pureAdmin.prototype.del_data = function (id) {
        if (typeof is_remember != "undefined")
            return false;
        if (typeof id != "undefined") {
            layui.data('tab_list', {
                key: id
                , remove: true
            });
        } else {
            layui.data('tab_list', null);
        }
    };
    /**
     * [del_other_data 删除其它]
     * @param  {[type]} id [description]
     * @return {[type]}    [description]
     */
    pureAdmin.prototype.del_other_data = function (id) {
        if (typeof is_remember != "undefined")
            return false;
        var tab_list = this.get_data();

        layui.data('tab_list', null);

        layui.data('tab_list', {
            key: id
            , value: tab_list[id]
        });
    };
    win.xadmin = new pureAdmin();

})(window);

window.onload=function(){
    layui.use(['layer', 'element', 'jquery'], function () {
        layer = layui.layer;
        element = layui.element;
        $ = layui.jquery;


        // 打开页面初始
        xadmin.init();

        //关闭tab清除记忆
        element.on('tabDelete(xbs_tab)', function (data) {
            var id = $(this).parent().attr('lay-id');
            xadmin.del_data(id);
        });
        //左侧菜单
        $('.left-nav #nav').on('click', 'li', function (event) {

            if ($(this).parent().attr('id') == 'nav') {
                xadmin.set_cate_data({key: 'f1', value: $('.left-nav #nav li').index($(this))})
                xadmin.set_cate_data({key: 'f2', value: null})
                xadmin.set_cate_data({key: 'f3', value: null})
            }

            if ($(this).parent().parent().parent().attr('id') == 'nav') {
                xadmin.set_cate_data({key: 'f2', value: $('.left-nav #nav li').index($(this))})
                xadmin.set_cate_data({key: 'f3', value: null})
            }

            if ($(this).parent().parent().parent().parent().parent().attr('id') == 'nav') {
                xadmin.set_cate_data({key: 'f3', value: $('.left-nav #nav li').index($(this))})
            }


            if ($('.left-nav').css('width') == '60px') {
                $('.left-nav').animate({width: '220px'}, 100);
                $('.page-content').animate({left: '220px'}, 100);
                $('.left-nav i').css('font-size', '14px');
                $('.left-nav cite,.left-nav .nav_right').show();
            }

            if ($(window).width() < 768) {
                $('.page-content-bg').show();
            }

            $('.left-nav').find('a').removeClass('active');
            $(this).children('a').addClass('active');
            if ($(this).children('.sub-menu').length) {
                if ($(this).hasClass('open')) {
                    $(this).removeClass('open');
                    $(this).find('.nav_right').html('&#xe697;');
                    $(this).children('.sub-menu').stop(true, true).slideUp();
                    $(this).siblings().children('.sub-menu').slideUp();
                } else {
                    $(this).addClass('open');
                    $(this).children('a').find('.nav_right').html('&#xe6a6;');
                    $(this).children('.sub-menu').stop(true, true).slideDown();
                    $(this).siblings().children('.sub-menu').stop(true, true).slideUp();
                    $(this).siblings().find('.nav_right').html('&#xe697;');
                    $(this).siblings().removeClass('open');
                }
            }
            event.stopPropagation();
        })
        var left_tips_index = null;
        $('.left-nav #nav').on('mouseenter', '.left-nav-li', function (event) {
            if ($('.left-nav').css('width') != '220px') {
                var tips = $(this).attr('lay-tips');
                left_tips_index = layer.tips(tips, $(this));
            }
        })

        $('.left-nav #nav').on('mouseout', '.left-nav-li', function (event) {
            layer.close(left_tips_index);
        })
        // 隐藏左侧
        $('.container .left_open i').click(function (event) {
            if ($('.left-nav').css('width') == '220px') {
                $('.left-nav .open').click();
                $('.left-nav i').css('font-size', '18px');
                $('.left-nav').animate({width: '60px'}, 100);
                $('.left-nav cite,.left-nav .nav_right').hide();
                $('.page-content').animate({left: '60px'}, 100);
                $('.page-content-bg').hide();
            } else {
                $('.left-nav').animate({width: '220px'}, 100);
                $('.page-content').animate({left: '220px'}, 100);
                $('.left-nav i').css('font-size', '14px');
                $('.left-nav cite,.left-nav .nav_right').show();
                if ($(window).width() < 768) {
                    $('.page-content-bg').show();
                }
            }

        });

        $('.page-content-bg').click(function (event) {
            $('.left-nav .open').click();
            $('.left-nav i').css('font-size', '18px');
            $('.left-nav').animate({width: '60px'}, 100);
            $('.left-nav cite,.left-nav .nav_right').hide();
            $('.page-content').animate({left: '60px'}, 100);
            $(this).hide();
        });

        $(".layui-tab-title").on('contextmenu', 'li', function (event) {
            var tab_left = $(this).position().left;
            var tab_width = $(this).width();
            var left = $(this).position().top;
            var this_index = $(this).attr('lay-id');
            $('#tab_right').css({'left': tab_left + tab_width / 2}).show().attr('lay-id', this_index);
            $('#tab_show').show();
            return false;
        });

        $('#tab_right').on('click', 'dd', function (event) {
            var data_type = $(this).attr('data-type');
            var lay_id = $(this).parents('#tab_right').attr('lay-id');
            if (data_type == 'this') {
                $('.layui-tab-title li[lay-id=' + lay_id + ']').find('.layui-tab-close').click();
            } else if (data_type == 'other') {
                $('.layui-tab-title li').eq(0).find('.layui-tab-close').remove();
                $('.layui-tab-title li[lay-id!=' + lay_id + ']').find('.layui-tab-close').click();
            } else if (data_type == 'all') {
                $('.layui-tab-title li[lay-id]').find('.layui-tab-close').click();
            }
            $('#tab_right').hide();
            $('#tab_show').hide();
        })


        $('.page-content,#tab_show,.container,.left-nav').click(function (event) {
            $('#tab_right').hide();
            $('#tab_show').hide();
        });

        // 页面加载完要做的
        xadmin.end();
    })
}
