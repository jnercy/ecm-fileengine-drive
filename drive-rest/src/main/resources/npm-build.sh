#!/usr/bin/env bash


#rm -rf /var/githome/nextcont-drive/
#echo  /var/githome/nextcont-drive/ remove complete...
cd /var/githome/nextcont-drive/

git pull  https://git.nextcont.com/Dev/nextcont-drive.git
echo -e "\033[41;37m git pull  complete...  \033[0m"

cd nextcont-drive/


npm run build
echo -e "\033[41;37m  build complete ...  \033[0m"
