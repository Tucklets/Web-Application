const webpack = require('webpack');
const CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
    entry: [
        './jsx/Root.jsx'
    ],
    output: {
        filename: 'bundle.js'
    },
    mode: 'production',
    devtool: '',
    plugins: [
        new CompressionPlugin({
            test: /\.(js|css|html|svg)$/,
            threshold: 10240,
            minRatio: 0.8
        })
    ],
    module: {
        rules: 
        [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loader: "babel-loader",
                options: {
                    presets: ['@babel/preset-env', '@babel/preset-react'],
                }
            },
            {   
                test: /\.(scss|css)$/,
                use: [
                    // Creates `style` nodes from JS strings
                    {loader: 'style-loader'},
                    // Translates CSS into CommonJS
                    {loader: 'css-loader'},
                    // Compiles Sass to CSS
                    {loader: 'sass-loader'},
                ]
            },
            {
                test: /\.(png|jpg|svg)$/,
                loader: 'url-loader',
                query: {
                    // Inline images smaller than 10kb as data URIs
                    limit: 10000
                }
            },
        ],
  },

    resolve: {
        extensions: ['.js', '.jsx', '.scss']
    }
};
