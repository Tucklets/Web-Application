const webpack = require('webpack');
const path = require('path');
module.exports = {
    entry: [
        // entry point
        './jsx/Main.jsx'
    ],
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js',
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        // enable HMR globally

        new webpack.NamedModulesPlugin(),
        // prints more readable module names in the browser console on HMR updates
    ],
    devServer: {
        contentBase: './dist',
        hot: true,
    },
    mode: 'development',
    devtool: 'source-map',
    module: {
        rules: [{
            test: /\.(js|jsx)$/,
            exclude: /node_modules/,
            use: [
                {loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env', '@babel/preset-react']
                }},
                {loader: 'react-hot-loader/webpack'}
            ]
        }]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    }
};