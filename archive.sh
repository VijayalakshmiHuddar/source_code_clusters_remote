 Volume in drive C has no label.
 Volume Serial Number is D8A5-E423

 Directory of C:\Users\Desktop\Data_archive_clusters

11-04-2019  19:18    <DIR>          .
11-04-2019  19:18    <DIR>          ..
11-04-2019  19:18                 0 archive.sh
11-04-2019  19:06               359 java.txt
11-04-2019  19:06               412 javaarchive.txt
11-04-2019  19:08               592 javaarchivecluster.txt
11-04-2019  19:08                 0 javaarchive_committed.txt
11-04-2019  19:07               469 javaarchive_one.txt
11-04-2019  19:09               643 meanstack.txt
11-04-2019  19:09    <DIR>          mongodb_cluster
11-04-2019  19:18               973 pythonarch.py
11-04-2019  19:15               864 pythoncluster.py
11-04-2019  19:10               751 pythoncluster.txt
11-04-2019  19:17               922 pythondataarchive.py
11-04-2019  19:15               810 pythondataarchive.txt
              12 File(s)          6,795 bytes
               3 Dir(s)   6,077,235,200 bytes free

oldfile=$1
newfile=$2

month=`date +%B`
year=`date +%Y`

prefix="frozenskys"

archivefile=$prefix.$month.$year.tar

# Check for existence of a compressed archive matching the naming convention
if [ -e $archivefile.gz ]
then
    echo "Archive file $archivefile already exists..."
    echo "Adding file '$oldfile' to existing tar archive..."

    # Uncompress the archive, because you can't add a file to a
    # compressed archive
    gunzip $archivefile.gz

    # Add the file to the archive
    tar --append --file=$archivefile $oldfile

    # Recompress the archive
    gzip $archivefile

# No existing archive - create a new one and add the file
else
    echo "Creating new archive file '$archivefile'..."
    tar --create --file=$archivefile $oldfile
    gzip $archivefile
fi

# Update the files outside the archive
mv $newfile $oldfile