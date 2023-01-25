if [ -n "$1" ]; then
  newSQLFile=../src/main/resources/db/migration/V`date +%Y%m%d%H%I%S`__${1}.sql
  touch $newSQLFile
  echo "new migration script generated at:"$newSQLFile
  else
    echo "请输入迁移脚本名称"

fi
