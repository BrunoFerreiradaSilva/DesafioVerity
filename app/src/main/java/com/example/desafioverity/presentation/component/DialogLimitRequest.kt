import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.desafioverity.domain.helpers.showDataConvertingString
import java.util.Date

@Composable
fun DialogLimitRequest(
    showDialog: Boolean,
    dateForRequest:String,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = "Limite de request atigindo")
            },
            text = {
                Text(text = "Tente novamente as  $dateForRequest")
            },
            confirmButton = {

            }
        )
    }
}
