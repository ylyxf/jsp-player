#!/usr/bin/env node

var arguments = process.argv.splice(2);
var webapp = require('path').resolve(__dirname,"../../"+arguments[0]);
var cmd = 'java -jar jsp-player.jar '+webapp+' '+ arguments[1];

require('child_process').execSync( cmd ,{
	stdio:[0,1,2],
	cwd:__dirname
});
