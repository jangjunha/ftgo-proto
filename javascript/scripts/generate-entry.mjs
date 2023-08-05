import { promises as fs } from "fs";

const dirs = await fs.readdir("lib/");

fs.writeFile(
    "lib/index.js",
    [
        "module.exports = {",
        ...dirs
            .filter(f => f.endsWith(".js"))
            .map(f => `  ${f.replace(/\.js$/, "")}: require("${f}"),`),
        "};",
    ].join("\n")
);

fs.writeFile(
    "lib/index.d.ts",
    dirs.filter(f => f.endsWith(".d.ts"))
        .map(f => `export * as ${f.replace(/\.d\.ts$/, "")} from "./${f}";`)
        .join("\n")
);
