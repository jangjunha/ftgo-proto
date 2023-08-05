import { promises as fs } from "fs";

const dirs = await fs.readdir("lib/");

fs.writeFile(
    "lib/index.js",
    dirs
        .filter(f => f.endsWith(".js"))
        .map(f => `export * from "./${f}";`)
        .join("\n")
);

fs.writeFile(
    "lib/index.d.ts",
    dirs
        .filter(f => f.endsWith(".d.ts"))
        .map(f => `export * from "./${f.replace(/\.d\.ts$/, "")}";`)
        .join("\n")
);
