# Notion Purge

**Work in Progess: Please do not use this script yet or you may lose your Notion pages**

_A Simple script to delete all pages in trash of a Notion's Workspace_

## Get Started

- Install

```shell
./sbtx "notionPurge / packInstall"
```

Make sure `$HOME/local/bin` is in your `PATH`.

## Usage

- Get Auth Token:
  - Go to notion.so
  - Open developer tools (hit F12)
  - Navigate to the Application tab (may be hidden if the developer window is small)
  - Expand Cookies under the Storage section on the sidebar
  - Click on `https://www.notion.so` to view all the cookies
  - Copy the value for the key `token_v2`

- Set `NOTION_TOKEN` environment variable as the copied value.

- Run:

```shell
java -jar notion-purge.jar <Workspace Name>
```

**Work in Progess: Please do not use this script yet or you may lose your Notion pages**
