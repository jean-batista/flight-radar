// CSS
import "./Message.css"

const Message = ({ type, message }) => {
  return (
    <div className={`message ${type === "SUCCESS" ? "message-success" : "message-error"}`}>
      {message}
    </div>
  )
}

export default Message;