#!/bin/bash

OLD_SMTP_HOST="\${smtp.host:localhost}"
NEW_SMTP_HOST="smtp.gmail.com"
OLD_SMTP_PORT="\${smtp.port:25}" 
NEW_SMTP_PORT="465"
OLD_SMTP_STARTTLS="\${smtp.starttls.enable:false}"
NEW_SMTP_STARTTLS="true"
OLD_SMTP_AUTH="\${smtp.auth:false}"
NEW_SMTP_AUTH="true"
OLD_SMTP_USERNAME="\${smtp.username:}"
NEW_SMTP_USERNAME="exoservice@gmail.com"
OLD_SMTP_PASS="\${smtp.password:}"
NEW_SMTP_PASS="exoadmin"
OLD_SMTP_SOCKET="\${smtp.socketFactory.port:}"
NEW_SMTP_SOCKET="465"
OLD_SMTP_SOCKET_CLASS="\${smtp.socketFactory.class:}"
NEW_SMTP_SOCKET_CLASS="javax.net.ssl.SSLSocketFactory"

DPATH="/home/selenium/platform-server/platform-4.0.x-SNAPSHOT/gatein/conf/configuration.properties"
BPATH="/home/selenium/platform-server/backup/configuration.properties"
TFILE="/tmp/out.tmp.$$"

[ ! -d $BPATH ] && mkdir -p $BPATH || :
for f in $DPATH
do
  if [ -f $f -a -r $f ]; then
    /bin/cp -f $f $BPATH
   sed "s/$OLD_SMTP_HOST/$NEW_SMTP_HOST/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_PORT/$NEW_SMTP_PORT/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_STARTTLS/$NEW_SMTP_STARTTLS/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_AUTH/$NEW_SMTP_AUTH/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_USERNAME/$NEW_SMTP_USERNAME/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_PASS/$NEW_SMTP_PASS/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_SOCKET/$NEW_SMTP_SOCKET/g" "$f" > $TFILE && mv $TFILE "$f"
   sed "s/$OLD_SMTP_SOCKET_CLASS/$NEW_SMTP_SOCKET_CLASS/g" "$f" > $TFILE && mv $TFILE "$f"
  else
   echo "Error: Cannot read $f"
  fi
done
#/bin/rm $TFILE
