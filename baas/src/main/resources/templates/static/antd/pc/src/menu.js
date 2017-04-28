/**
 * 定义sidebar中的菜单项
 *
 * 一些约定:
 * 1.菜单最多3层;
 * 2.只有顶层的菜单可以带图标;
 * 3.只有"叶子"节点才能跳转;
 * 4.所有的key都不能重复;
 */

module.exports = [
  {
    key: 'index',  // route时url中的值
    name: '数据大盘',  // 在菜单中显示的名称
    icon: 'smile',  // 只有顶层菜单可以带图标
    child: [
      {
        key: 'option1',
        name: '数据报表1',
      },
      {
        key: 'option2',
        name: '数据报表2',
      },
      {
        key: 'option3',
        name: '数据报表3',
      },
    ],
  },
  {
    key: 'daohang',
    name: '设备管理',
    icon: 'appstore',
    child: [
      {
        key: '555',
        name: '选项5',
      }
    ],
  },
  {
    key: 'test',
    name: '信息管理',
    icon: 'eye',
    child: [
      {
        key: 'aaa',
        name: '选项a',
      }
    ],
  },
];
