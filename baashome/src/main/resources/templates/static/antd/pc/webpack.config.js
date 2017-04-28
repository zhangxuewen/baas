const webpack = require('atool-build/lib/webpack');

module.exports = function(webpackConfig, env) {
  webpackConfig.babel.plugins.push('transform-runtime');

  // Support hmr
  if (env === 'development') {
    webpackConfig.devtool = '#eval';
    webpackConfig.babel.plugins.push('dva-hmr');
  } else {
    webpackConfig.babel.plugins.push('dev-expression');
  }

  // Don't extract common.js and common.css
  webpackConfig.plugins = webpackConfig.plugins.filter(function(plugin) {
    return !(plugin instanceof webpack.optimize.CommonsChunkPlugin);
  });
  webpackConfig.babel.plugins.push(['import', {
    libraryName: 'antd',
    style: true,
  }]);


  return webpackConfig;
};
