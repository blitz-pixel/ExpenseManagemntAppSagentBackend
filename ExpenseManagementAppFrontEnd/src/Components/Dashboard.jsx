import { WalletCards, Receipt, FileText, FolderKanban } from "lucide-react"
import { Link } from "react-router-dom";
import { Grid2, Box , Paper,Typography} from "@mui/material";

function Dashboard() {
      return (
        <Box
  sx={{
    display: "grid",
    position: "relative",
    border: '1px dashed grey' ,
    justifyContent: "center",
    gridTemplateColumns: { xs: "1fr", md: "1fr 1fr" }, // Two columns on medium screens
    gridTemplateRows: { xs: "auto", md: "200px 200px" }, // Two rows on medium screens
    rowGap: "70px",
    columnGap: "20px",
    // padding: "20px",
    width: "800px",
    height: "520px",
    
  }}
>

<Link to="/income">
    
  <Paper
    sx={{
      height: "250px",
      width: "398px",
      display: "flex",

      alignItems: "center",
      justifyContent: "center",
      gridColumn: { xs: "1", md: "1 / span 1" }, // First column on medium screens
      gridRow: { xs: "1", md: "1" }, // First row on medium screens
    }}
  >
    <Typography variant="h4">Income</Typography>
    <Box sx={{
      border: '1px dashed grey',
      position: 'absolute',
      right: '510px',
    }}>
      <WalletCards />
    </Box>
  </Paper>
  </Link>

  
       <Link to="/expense">
        
            
          
      
  <Paper
    sx={{
       height: "250px",
      width: "380px",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      gridColumn: { xs: "1", md: "2" }, // Second column on medium screens
      gridRow: { xs: "2", md: "1" }, // First row on medium screens
    }}
  >
    <Typography variant="h4">Revenue</Typography>
    <Box sx={{
      border: '1px dashed grey',
      position: 'absolute',
      right: '90px',
    }}>
    <Receipt/>
    </Box>
  </Paper>
 </Link>
  <Paper
    sx={{
      height: "250px",
      width: "398px",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      gridColumn: { xs: "1", md: "1" }, // First column on medium screens
      gridRow: { xs: "3", md: "2" }, // Second row on medium screens
    }}
  >
    <Typography variant="h4">3</Typography>
  </Paper>


  <Paper
    sx={{
      height: "250px",
      width: "380px",
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      gridColumn: { xs: "1", md: "2" }, // Second column on medium screens
      gridRow: { xs: "4", md: "2" }, // Second row on medium screens
    }}
  >
    <Typography variant="h4">4</Typography>
  </Paper>  
</Box>
      );

  // return (
  //   <div className="grid grid-cols-1 md:grid-cols-2 gap-6 p-6">
  //     <Link to="/income">
  //       <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
  //         <div className="flex items-center justify-between mb-4">
  //           <h2 className="text-2xl font-bold">INCOME OVERVIEW</h2>
  //           <WalletCards className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
  //         </div>
  //       </div>
  //     </Link>


  //     <Link to="/report">
  //       <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
  //         <div className="flex items-center justify-between mb-4">
  //           <h2 className="text-2xl font-bold">REPORT</h2>
  //           <FileText className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
  //         </div>
  //       </div>
  //     </Link>

  //     <Link to="/categories">
  //       <div className="group bg-[#ffd9d1] rounded-lg p-8 transition-all hover:shadow-lg">
  //         <div className="flex items-center justify-between mb-4">
  //           <h2 className="text-2xl font-bold">CATEGORIES</h2>
  //           <FolderKanban className="h-6 w-6 text-gray-700 group-hover:scale-110 transition-transform" />
  //         </div>
  //       </div>
  //     </Link>
  //   </div>
  // )
}

export default Dashboard;