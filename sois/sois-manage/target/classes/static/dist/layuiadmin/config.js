/**

 @Name：layuiAdmin iframe版全局配置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL（layui付费产品协议）

 */

layui.define(['laytpl', 'layer', 'element', 'util'], function(exports){
    exports('setter', {
        container: 'LAY_app'
        ,base: layui.cache.base
        ,views: layui.cache.base + 'tpl/'
        ,entry: 'index'
        ,engine: '.html'
        ,pageTabs: true

        ,name: 'layuiAdmin'
        ,tableName: 'layuiAdmin'
        ,MOD_NAME: 'admin'

        ,debug: true

        /*自定义请求字段*/
        ,request: {
            tokenName: "token"
        }
        /*自定义响应字段*/
        ,response: {
            statusName: 'code'
            ,statusCode: {
                ok: 0 //数据状态一切正常的状态码
                ,logout: 1001 //登录状态失效的状态码
                ,error: -1  //增加登录失败的状态码
            }
            ,msgName: 'msg'
            ,dataName: 'data'
        }

        /*扩展的第三方模块*/
        ,extend: [
            'echarts',
            'echartsTheme'
        ]

        /*主题配置*/
        ,theme: {
            color: [{
                main: '#20222A'
                ,selected: '#009688'
                ,alias: '默认'/*default*/
            },{
                main: '#03152A'
                ,selected: '#3B91FF'
                ,alias: '藏蓝'/*dark-blue*/
            },{
                main: '#2E241B'
                ,selected: '#A48566'
                ,alias: '咖啡'/*coffee*/
            },{
                main: '#50314F'
                ,selected: '#7A4D7B'
                ,alias: '紫红'/*purple-red*/
            },{
                main: '#344058'
                ,logo: '#1E9FFF'
                ,selected: '#1E9FFF'
                ,alias: '海洋'/*ocean*/
            },{
                main: '#3A3D49'
                ,logo: '#2F9688'
                ,selected: '#5FB878'
                ,alias: '墨绿'/*green*/
            },{
                main: '#20222A'
                ,logo: '#F78400'
                ,selected: '#F78400'
                ,alias: '橙色'/*red*/
            },{
                main: '#28333E'
                ,logo: '#AA3130'
                ,selected: '#AA3130'
                ,alias: '时尚红'/*fashion-red*/
            },{
                main: '#24262F'
                ,logo: '#3A3D49'
                ,selected: '#009688'
                ,alias: '经典黑'/*classic-black*/
            },{
                logo: '#226A62'
                ,header: '#2F9688'
                ,alias: '墨绿头r'/*green-heade*/
            },{
                main: '#344058'
                ,logo: '#0085E8'
                ,selected: '#1E9FFF'
                ,header: '#1E9FFF'
                ,alias: '海洋头'/*ocean-header*/
            },{
                header: '#393D49'
                ,alias: '经典黑头'/*classic-black-header*/
            },{
                main: '#50314F'
                ,logo: '#50314F'
                ,selected: '#7A4D7B'
                ,header: '#50314F'
                ,alias: '紫红头'/*purple-red-header*/
            },{
                main: '#28333E'
                ,logo: '#28333E'
                ,selected: '#AA3130'
                ,header: '#AA3130'
                ,alias: '时尚红头'/*fashion-red-header*/
            },{
                main: '#28333E'
                ,logo: '#009688'
                ,selected: '#009688'
                ,header: '#009688'
                ,alias: '墨绿头'/*green-header*/
            }]

            /*初始的颜色索引，对应上面的配色方案数组索引*/
            /*如果本地已经有主题色记录，则以本地记录为优先，除非请求本地数据（localStorage）*/
            ,initColorIndex: 0
        }
    });
});
