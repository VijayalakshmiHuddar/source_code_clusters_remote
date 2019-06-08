 Volume in drive C has no label.
 Volume Serial Number is D8A5-E423

 Directory of C:\Users\CHITTU\Desktop\Data_archive_clusters

11-04-2019  19:16    <DIR>          .
11-04-2019  19:16    <DIR>          ..
11-04-2019  19:06               359 java.txt
11-04-2019  19:06               412 javaarchive.txt
11-04-2019  19:08               592 javaarchivecluster.txt
11-04-2019  19:08                 0 javaarchive_committed.txt
11-04-2019  19:07               469 javaarchive_one.txt
11-04-2019  19:09               643 meanstack.txt
11-04-2019  19:09    <DIR>          mongodb_cluster
11-04-2019  19:15               864 pythoncluster.py
11-04-2019  19:10               751 pythoncluster.txt
11-04-2019  19:17                 0 pythondataarchive.py
11-04-2019  19:15               810 pythondataarchive.txt
              10 File(s)          4,900 bytes
               3 Dir(s)   6,077,243,392 bytes free

import os
import shutil
from zipfile import ZipFile
from os import path
from shutil import make_archive

def main():
# Check if file exists
	if path.exists("guru99.txt"):
# get the path to the file in the current directory
	src = path.realpath("guru99.txt");
# rename the original file
	os.rename("career.guru99.txt","guru99.txt")
# now put things into a ZIP archive
	root_dir,tail = path.split(src)
    shutil.make_archive("guru99 archive", "zip", root_dir)
# more fine-grained control over ZIP files
	with ZipFile("testguru99.zip","w") as newzip:
	newzip.write("guru99.txt")
	    newzip.write("guru99.txt.bak")
if __name__== "__main__":
	  main()

