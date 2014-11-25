
#!/bin/sh
# Install Dependency :
# Debian/Ubuntu : aptitude install gitstat gource perl

echo "Stat script generator by TACTfactory 2012\n########################################################"""

echo "Clean stat..."
rm -rf ./doc/stats/* > /dev/null

echo "Generate HTML statistique..."
/usr/bin/gitstats ./ ./doc/stats/html/ > /dev/null

echo "Fetch Gravatar image of commiters..."
./gravatar.pl > /dev/null

echo "Generate Gource video..."
/usr/bin/gource --title "lifetime thiefaine" --stop-at-end --auto-skip-seconds 1 --disable-auto-skip --max-files 0 --date-format "%d %b %y" --hide usernames,dirnames,mouse --seconds-per-day .25 --file-filter 'libs/|doc/|vendor/' -user-image-dir .git/avatar/ -1280x720 -o - | avconv -y -r 30 -f image2pipe -vcodec ppm -i - -vcodec libvpx -b 65536K doc/stats/lifetime.webm > /dev/null

echo "Done !"
